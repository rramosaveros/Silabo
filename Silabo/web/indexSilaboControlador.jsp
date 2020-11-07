
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="ec.edu.espoch.academico.Periodo"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        String tipo = request.getParameter("tipo");
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        Periodo periodo = port.periodoActual();

        if (tipo != null) {
            if (tipo.equals("silabo")) {
//                Silabo silabo = new Silabo();
//                silabo.setCodCarrera(request.getParameter("codCarrera"));
//                silabo.setCodMateria(request.getParameter("codMateria"));
//                silabo.setPeriodo(periodo.getCodigo());
//                Gson G = new Gson();
//                String AsignaturaInfo = G.toJson(silabo);
//                session.setAttribute("jsonAsignaturaInfo", AsignaturaInfo);
                
            }
            if (tipo.equals("Coordinador")) {
                Silabo silabo = new Silabo();
                silabo.setCedula(request.getParameter("cedula"));
                silabo.setCodCarrera(request.getParameter("codCarrera"));
                silabo.setCodMateria(request.getParameter("codMateria"));
                silabo.setPeriodo(periodo.getCodigo());
                silabo.setRol("Coordinador");
                silabo.setCampoFormacion("PRAX");
                Gson G = new Gson();
                String jsonAsignaturaCoordinador = G.toJson(silabo);
                session.setAttribute("jsonAsignaturaCoordinador", jsonAsignaturaCoordinador);
                session.setAttribute("Rol", "Coordinador");
            } else {
                String jsonSilabo = request.getParameter("jsonSilabo");
                session.setAttribute("jsonSilabo", jsonSilabo);
            }
            response.sendRedirect("indexSilaboModelo.jsp?tipo=" + tipo);
        } else {
            response.sendRedirect("Inicio/indexInicio.jsp");
        }
    }
%>    