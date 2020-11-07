<%@page import="dda.silabo.criteriosevaluaciones.comunes.Calificacion"%>
<%@page import="dda.silabo.criteriosevaluaciones.comunes.CriteriosEvaluaciones"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.criteriosevaluaciones.iu.CriteriosEvaluacionesIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.JSONObject"%>

<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        
        String opcion = request.getParameter("opcion");
        Gson G = new Gson();
        try {
            if (opcion.equals("getEvaluaciones")) {
                String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
                Silabo silabo1 = G.fromJson(jsonAsignaturaInfo, Silabo.class);
                String jsonSilabo = (String) session.getAttribute("jsonSilabo");
                Silabo silabo2 = G.fromJson(jsonSilabo, Silabo.class);
                silabo1.setIdSilabo(silabo2.getIdSilabo());
                silabo1.setRol(silabo2.getRol());
                silabo1.setTipo(silabo2.getTipo());
                silabo1.setIdTipo(silabo2.getIdTipo());
                String jsonEvaluaciones = port.evaluacionesCargar(G.toJson(silabo1));
                CriteriosEvaluacionesIU evaluaciones = G.fromJson(jsonEvaluaciones, CriteriosEvaluacionesIU.class);
                session.setAttribute("Evaluaciones", evaluaciones);
                response.sendRedirect("CriteriosEvaluacionesControlador.jsp?opcion=show&accion=view");
            }
            if (opcion.equals("saveEvaluaciones")) {
                String jsonMenuSilabo = "";
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonEvaluaciones = (String) session.getAttribute("jsonEvaluaciones");
                Silabo jsonMenuSilaboTransformadoSilabo = G.fromJson(jsonMenuSilabo, Silabo.class); //nuevo
                String codCarrera = jsonMenuSilaboTransformadoSilabo.getCodCarrera();
                JSONObject jsonEvaluacionesInsertarCodCarrera = new JSONObject(jsonEvaluaciones); //nuevo
                jsonEvaluacionesInsertarCodCarrera.getJSONObject("silabos").put("codCarrera",codCarrera); //nuevo            
                String jsonSilabosExtraidoDeEvaluaciones = jsonEvaluacionesInsertarCodCarrera.getJSONObject("silabos").toString();
                String jsonEvaluacionesExtraidoDeEvaluaciones = jsonEvaluacionesInsertarCodCarrera.getString("evaluaciones");
                String jsonObservacionesExtraidoDeEvaluaciones = jsonEvaluacionesInsertarCodCarrera.getString("observaciones");
                String jsonEvaluacionesConCodCarrera = "{\"evaluaciones\":"+jsonEvaluacionesExtraidoDeEvaluaciones+",\"silabos\":"+jsonSilabosExtraidoDeEvaluaciones+",\"observaciones\":"+jsonObservacionesExtraidoDeEvaluaciones+"}";//modifique
                String resultado = port.calificacionesGuardar(jsonEvaluacionesConCodCarrera); //modificado               
                CriteriosEvaluacionesIU evaluaciones = G.fromJson(resultado, CriteriosEvaluacionesIU.class);
                session.setAttribute("Evaluaciones", evaluaciones);
                String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
                MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
                session.setAttribute("MenuLateral", menulateral);
                response.sendRedirect("CriteriosEvaluacionesControlador.jsp?opcion=show&accion=save");
            }
        } catch (Exception e) {
            Logger.getLogger("CriteriosEvaluacionesModelo.jsp").log(Level.SEVERE, "Silabo/4CriteriosEvaluaciones/CriteriosEvaluacionesModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }
%>
