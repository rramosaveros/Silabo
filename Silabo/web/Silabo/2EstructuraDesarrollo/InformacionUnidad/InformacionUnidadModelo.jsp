<%@page import="dda.silabo.estructura.unidadinformacion.comunes.EstructuraDesarrollo"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.estructura.unidad.info.iu.InformacionUnidadIU"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="com.google.gson.Gson"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        try {
            dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
            dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
            Gson G = new Gson();
            String opcion = request.getParameter("opcion");
            if (opcion.equals("getInformacion")) {
                String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonSilabo = (String) session.getAttribute("jsonSilabo");
                Silabo asignaturainfo = G.fromJson(jsonAsignaturaInfo, Silabo.class);
                Silabo silabo = G.fromJson(jsonSilabo, Silabo.class);
                asignaturainfo.setIdUnidad(silabo.getIdUnidad());
                asignaturainfo.setIdSilabo(silabo.getIdSilabo());
                asignaturainfo.setRol(silabo.getRol());
                asignaturainfo.setIdTipo(silabo.getIdTipo());
                String jsonInformacionUnidad = port.unidadInformacionCargar(G.toJson(asignaturainfo));
                InformacionUnidadIU informacion = G.fromJson(jsonInformacionUnidad, InformacionUnidadIU.class);
                session.setAttribute("InformacionUnidad", informacion);
                response.sendRedirect("InformacionUnidadControlador.jsp?opcion=show&accion=view");
            } else if (opcion.equals("saveEstructura")) {
                String jsonMenuSilabo = "";
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");

                String jsonEstructura = (String) session.getAttribute("jsonEstructura");
                 EstructuraDesarrollo estructuraAD = G.fromJson(jsonEstructura, EstructuraDesarrollo.class);
                 String jsonSilabo = (String) session.getAttribute("jsonSilabo");
                Silabo asignaturainfo = estructuraAD.getSilabos(); 
                Silabo silabo = G.fromJson(jsonSilabo, Silabo.class);
                asignaturainfo.setIdUnidad(silabo.getIdUnidad());
                asignaturainfo.setIdSilabo(silabo.getIdSilabo());
                asignaturainfo.setRol(silabo.getRol());
                asignaturainfo.setIdTipo(silabo.getIdTipo());
                String jsonInformacionUnidad = port.unidadInformacionGuardar(jsonEstructura);
                String jsonInformacionUnidad2 = port.unidadInformacionCargar(G.toJson(asignaturainfo)); 
                InformacionUnidadIU informacion = G.fromJson(jsonInformacionUnidad2, InformacionUnidadIU.class);
                String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
                MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
                session.setAttribute("MenuLateral", menulateral);
                session.setAttribute("InformacionUnidad", informacion);
                response.sendRedirect("InformacionUnidadControlador.jsp?opcion=show&accion=save");
            } else if (opcion.equals("deleteTema")) {
                String jsonMenuSilabo = "";
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");

                String jsonEstructura = request.getParameter("jsonEstructura");
                String jsonInformacionUnidad = port.temaEliminar(jsonEstructura);
                String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
                MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
                session.setAttribute("MenuLateral", menulateral);
                InformacionUnidadIU informacion = G.fromJson(jsonInformacionUnidad, InformacionUnidadIU.class);
                session.setAttribute("InformacionUnidad", informacion);
                response.sendRedirect("InformacionUnidadControlador.jsp?opcion=show&accion=save");
            } else if (opcion.equals("deleteSubtema")) {
                String jsonMenuSilabo = "";

                String jsonEstructura = request.getParameter("jsonEstructura");
                String jsonInformacionUnidad = port.subtemaEliminar(jsonEstructura);
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
                MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
                session.setAttribute("MenuLateral", menulateral);
                InformacionUnidadIU informacion = G.fromJson(jsonInformacionUnidad, InformacionUnidadIU.class);
                session.setAttribute("InformacionUnidad", informacion);
                response.sendRedirect("InformacionUnidadControlador.jsp?opcion=show&accion=save");
            } else if (opcion.equals("gestionarUnidades")) {
                String jsonMenuSilabo = "";
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
                Silabo silabo = new Gson().fromJson(jsonMenuSilabo, Silabo.class);
                String result = port.unidadInformacionSilabo(silabo.getCodCarrera(), silabo.getCodMateria(), silabo.getPeriodo());
                InformacionUnidadIU informacion = G.fromJson(result, InformacionUnidadIU.class);
                session.setAttribute("InformacionUnidadIU", informacion);
                response.sendRedirect("InformacionUnidadControlador.jsp?opcion=show&accion=gestionarUnidades");
            } else if (opcion.equals("reestablecer")) {
                String json = request.getParameter("json");
                String jsonInformacionUnidad = port.unidadInformacionGuardarReestablecer(json);
                InformacionUnidadIU informacion = G.fromJson(jsonInformacionUnidad, InformacionUnidadIU.class);
                session.setAttribute("InformacionUnidadIU", informacion);
                response.sendRedirect("InformacionUnidadControlador.jsp?opcion=show&accion=gestionarUnidades");
            }
        } catch (Exception e) {
            Logger.getLogger("InformacionUnidadModelo.jsp").log(Level.SEVERE, "Silabo/2EstructuraDesarrollo/InformacionUnidad/InformacionUnidadModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }
%>
