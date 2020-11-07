<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasMetodologicasIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getEstrategias") && jsonSilabo != null) {
                response.sendRedirect("EstrategiasModelo.jsp?opcion=getEstrategias");
            } else if (opcion.equals("dibujarLista")) {
                String id = request.getParameter("id");
                session.setAttribute("id", id);
                EstrategiasMetodologicasIU estrategias = (EstrategiasMetodologicasIU) session.getAttribute("Estrategias");
                if (estrategias != null) {
                    response.sendRedirect("EstrategiasVista.jsp?accion=dibujarLista");
                } else {
                    response.sendRedirect("EstrategiasModelo.jsp?opcion=getEstrategias");
                }
            } else if (opcion.equals("show")) {
                String accion = request.getParameter("accion");
                response.sendRedirect("EstrategiasVista.jsp?accion=" + accion);
            } else if (opcion.equals("saveEstrategias")) {
                String jsonEstrategias = request.getParameter("jsonEstrategias");
                response.sendRedirect("EstrategiasModelo.jsp?opcion=saveEstrategias&jsonEstrategias=" + jsonEstrategias);
            }

        }
    }
%>
