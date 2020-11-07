
<%@page import="dda.silabo.parametros.iu.ParametrosIU"%>
<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.opciones.iu.OpcionesIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
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
        if (opcion.equals("getParametros")) {
            String jsonSilabo = (String) session.getAttribute("jsonSilabo");
            String jsonparametros = port.parametrosCargar(jsonSilabo);
            ParametrosIU parametrosiu = G.fromJson(jsonparametros, ParametrosIU.class);
            session.setAttribute("Parametros", parametrosiu);
            response.sendRedirect("ParametrosControlador.jsp?opcion=show");
        }
        if (opcion.equals("saveParametros")) {
            String jsonSilabo = request.getParameter("jsonSilabo");
            String jsonParametros = (String) session.getAttribute("jsonParametros");
            String result = port.parametrosGuardar(jsonParametros, jsonSilabo);
            ParametrosIU parametrosiu = G.fromJson(result, ParametrosIU.class);
            session.setAttribute("Parametros", parametrosiu);
            response.sendRedirect("ParametrosControlador.jsp?opcion=show");
        }

    }
%>   