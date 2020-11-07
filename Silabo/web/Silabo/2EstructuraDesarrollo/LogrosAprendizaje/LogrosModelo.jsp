<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.estructura.unidad.logros.iu.LogrosIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        String opcion = request.getParameter("opcion");
        Gson G = new Gson();
        try {
            if (opcion.equals("getLogros")) {
                String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonSilabo = (String) session.getAttribute("jsonSilabo");
                Silabo asignaturainfo = G.fromJson(jsonAsignaturaInfo, Silabo.class);
                Silabo jsonSilaboAumentadoCodCarrera = G.fromJson(jsonSilabo, Silabo.class);
                jsonSilaboAumentadoCodCarrera.setCodCarrera(asignaturainfo.getCodCarrera());
                String jsonLogros = port.logroCargar(G.toJson(jsonSilaboAumentadoCodCarrera));
                LogrosIU logros = G.fromJson(jsonLogros, LogrosIU.class);
                session.setAttribute("Logros", logros);
                response.sendRedirect("LogrosControlador.jsp?opcion=show&accion=view");
            }
            if (opcion.equals("saveLogro")) {
                String jsonMenuSilabo = "";
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonLogro = (String) session.getAttribute("jsonLogro");
                String resultado = port.logroGuardar(jsonLogro).toString();
                if (resultado.equals("ok")) {
                    LogrosIU logros = G.fromJson(jsonLogro, LogrosIU.class);
                    resultado = port.logroCargar(G.toJson(logros.getSilabos()));
                }else {
                response.sendError(300, "Error al guardar");
                }
                LogrosIU logros = G.fromJson(resultado, LogrosIU.class);
                session.setAttribute("Logros", logros);
                String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
                MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
                session.setAttribute("MenuLateral", menulateral);
                response.sendRedirect("LogrosControlador.jsp?opcion=show&accion=save");
            }
        } catch (Exception e) {
            Logger.getLogger("LogrosModelo.jsp").log(Level.SEVERE, "Silabo/2EstructuraDesarrollo/LogrosAprendizaje/LogrosModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }
%>
