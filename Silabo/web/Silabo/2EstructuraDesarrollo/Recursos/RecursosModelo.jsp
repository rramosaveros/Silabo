<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.estructura.unidad.recursos.iu.RecursosIU"%>
<%@page import="com.google.gson.Gson"%>

<%
    dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
    dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
    String opcion = request.getParameter("opcion");
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    Gson G = new Gson();
    try {
        if (opcion.equals("getRecursos")) {
            String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
            String jsonSilabo = (String) session.getAttribute("jsonSilabo");
            Silabo asignaturainfo = G.fromJson(jsonAsignaturaInfo, Silabo.class);
            Silabo jsonSilaboAumentadoCodCarrera = G.fromJson(jsonSilabo, Silabo.class);
            jsonSilaboAumentadoCodCarrera.setCodCarrera(asignaturainfo.getCodCarrera());
            String jsonRecursos = port.recursosCargar(G.toJson(jsonSilaboAumentadoCodCarrera));
            RecursosIU recursos = G.fromJson(jsonRecursos, RecursosIU.class);
            session.setAttribute("Recursos", recursos);
            response.sendRedirect("RecursosControlador.jsp?opcion=show&accion=view");
        }
        if (opcion.equals("saveRecursos")) {
            String jsonMenuSilabo = "";
            jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
            String jsonR = request.getParameter("jsonR");
            String resultado = port.recursosGuardar(jsonR);
            if (resultado.equals("ok")) {
                RecursosIU recursos = G.fromJson(jsonR, RecursosIU.class);
                resultado = port.recursosCargar(G.toJson(recursos.getSilabos()));
            } else {
                response.sendError(300, "Error al guardar");
            }
            RecursosIU recursos = G.fromJson(resultado, RecursosIU.class);
            session.setAttribute("Recursos", recursos);
            String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
            MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
            session.setAttribute("MenuLateral", menulateral);
            response.sendRedirect("RecursosControlador.jsp?opcion=show&accion=save");
        }
    } catch (Exception e) {
        Logger.getLogger("RecursosModelo.jsp").log(Level.SEVERE, "Silabo/2EstructuraDesarrollo/Recursos/RecursosModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
        System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
    }
%>
