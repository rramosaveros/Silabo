<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="dda.silabo.datosgenerales.iu.DatosGeneralesIU"%>
<%@page import="com.google.gson.Gson"%>

<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        String opcion = request.getParameter("opcion");
        String jsonDatosGeneralesCargar = "";
        if (opcion.equals("getDatosGenerales")) {
            String codMateria = (String) session.getAttribute("codMateria");
            String codCarrera = (String) session.getAttribute("codCarrera");
            DatosGeneralesIU datosgenerales = (DatosGeneralesIU) session.getAttribute("DatosGenerales_" + logueo.getCedula() + codCarrera + codMateria);
            if (datosgenerales == null) {
                jsonDatosGeneralesCargar = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonDatosGenerales = port.datosGeneralesCargar(jsonDatosGeneralesCargar);
                Gson G = new Gson();
                datosgenerales = G.fromJson(jsonDatosGenerales, DatosGeneralesIU.class);
                session.setAttribute("DatosGenerales_" + logueo.getCedula() + codCarrera + codMateria, datosgenerales);
            }
            response.sendRedirect("DatosGeneralesControlador.jsp?opcion=show");
        }
    }
%>
