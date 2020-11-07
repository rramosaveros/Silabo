<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.estructura.unidad.actividades.iu.ActividadesIU"%>
<%@page import="com.google.gson.Gson"%>
<%
//    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
//    if (logueo == null) {
//        response.sendRedirect("../../../login/loginControlador.jsp");
//    } else {
    dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
    dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
    String opcion = request.getParameter("opcion");
    Gson G = new Gson();
    try {
        if (opcion.equals("getActividades")) {
            String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
            String jsonSilabo = (String) session.getAttribute("jsonSilabo");
            Silabo asignaturainfo = G.fromJson(jsonAsignaturaInfo, Silabo.class);
            Silabo jsonSilaboAumentadoCodCarrera = G.fromJson(jsonSilabo, Silabo.class);
            jsonSilaboAumentadoCodCarrera.setCodCarrera(asignaturainfo.getCodCarrera());            
            String jsonActividades = port.actividadesAprendizajeCargar(G.toJson(jsonSilaboAumentadoCodCarrera));
            ActividadesIU actividades = G.fromJson(jsonActividades, ActividadesIU.class);
            session.setAttribute("Actividades", actividades);
            response.sendRedirect("ActividadesControlador.jsp?opcion=show&accion=view");
        } else if (opcion.equals("saveActividades")) {
            String jsonMenuSilabo = "";
            jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
            String jsonActividades = (String) session.getAttribute("jsonActividades");
            String resultado = port.actividadesAprendizajeGuardar(jsonActividades);
            if (resultado.equals("ok")) {
                ActividadesIU actividades = G.fromJson(jsonActividades, ActividadesIU.class);
                resultado = port.actividadesAprendizajeCargar(G.toJson(actividades.getSilabos()));
            } else {
                response.sendError(300, "Error al guardar");
            }
            ActividadesIU actividades = G.fromJson(resultado, ActividadesIU.class);
            session.setAttribute("Actividades", actividades);
            String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
            MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
            session.setAttribute("MenuLateral", menulateral);
            response.sendRedirect("ActividadesControlador.jsp?opcion=show&accion=save");
        } else if (opcion.equals("deleteActividadUsuario")) {
            String jsonMenuSilabo = "";
            jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
            String jsonActividades = request.getParameter("jsonActividades");
            String resultado = port.actividadesEliminar(jsonActividades);
            ActividadesIU actividades = G.fromJson(resultado, ActividadesIU.class);
            session.setAttribute("Actividades", actividades);
            String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
            MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
            session.setAttribute("MenuLateral", menulateral);
            response.sendRedirect("ActividadesControlador.jsp?opcion=show&accion=save");
        }
    } catch (Exception e) {
        Logger.getLogger("ActividadesModelo.jsp").log(Level.SEVERE, "Silabo/2EstructuraDesarrollo/ActividadesAprendizaje/ActividadesModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
        System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
    }
//    }

%>