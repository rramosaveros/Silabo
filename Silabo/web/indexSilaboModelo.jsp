
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
    dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
    String tipo = request.getParameter("tipo");
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
    session.setAttribute("idSilabo", port.silaboIdCargar(jsonAsignaturaInfo));
    if (logueo == null) {
        response.sendRedirect("login/loginControlador.jsp");
    }
    if (tipo.equals("inicio")) {
        response.sendRedirect("Silabo/6DatosDocentes/DatosDocentesControlador.jsp?opcion=getDocenteInicio");
    }

    if (tipo.equals("DatosGenerales")) {
        response.sendRedirect("Silabo/1DatosGenerales/DatosGeneralesControlador.jsp?opcion=getDatosGenerales");
    }
    if (tipo.equals("informacionUnidad")) {
        response.sendRedirect("Silabo/2EstructuraDesarrollo/InformacionUnidad/InformacionUnidadControlador.jsp?opcion=getInformacion");
    }
    if (tipo.equals("Objetivos")) {
        response.sendRedirect("Silabo/2EstructuraDesarrollo/Objetivos/ObjetivosControlador.jsp?opcion=getObjetivos");
    }
    if (tipo.equals("Recursos")) {
        response.sendRedirect("Silabo/2EstructuraDesarrollo/Recursos/RecursosControlador.jsp?opcion=getRecursos");
    }
    if (tipo.equals("Estrategias")) {
        response.sendRedirect("Silabo/2EstructuraDesarrollo/EstrategiasMetodologicas/EstrategiasControlador.jsp?opcion=getEstrategias");
    }

    if (tipo.equals("Autonomas") || (tipo.equals("Aula"))) {
        response.sendRedirect("Silabo/2EstructuraDesarrollo/ActividadesAprendizaje/ActividadesControlador.jsp?opcion=getActividades&tipo=" + tipo);
    }
    if (tipo.equals("Logros")) {
        response.sendRedirect("Silabo/2EstructuraDesarrollo/LogrosAprendizaje/LogrosControlador.jsp?opcion=getLogros");
    }
    if (tipo.equals("Real") || tipo.equals("Virtual") || tipo.equals("Aulico")) {
        response.sendRedirect("Silabo/3EscenarioAprendizaje/EscenariosControlador.jsp?opcion=getEscenarios");
    }
    if (tipo.equals("Criterios")) {
        response.sendRedirect("Silabo/4CriteriosEvaluaciones/CriteriosEvaluacionesControlador.jsp?opcion=getEvaluaciones");
    }
    if (tipo.equals("Bibliografias")) {
        response.sendRedirect("Silabo/5Bibliografias/BibliografiasControlador.jsp?opcion=getBibliografias");
    }
    if (tipo.equals("Docente")) {
        response.sendRedirect("Silabo/6DatosDocentes/DatosDocentesControlador.jsp?opcion=getDatosDocente");
    }

    if (tipo.equals("mnuAdministrador")) {
        response.sendRedirect("Silabo/MenuLateral/MenuLateralControlador.jsp?opcion=getMenuAdministrador");
    }
    if (tipo.equals("silabo")) {
        if (!logueo.getRolActivo().equals("Adm")) {
            session.setAttribute("accion", "inicio");
        } else {
            session.setAttribute("accion", "mnuAdministrador");
        }
        response.sendRedirect("index.jsp");
    }
    if (tipo.equals("Coordinador")) {
        session.setAttribute("accion", "coordinador");
        response.sendRedirect("index.jsp");
    }
    if (tipo.equals("importar")) {
        response.sendRedirect("ImportarSilabo/ImportarControlador.jsp?opcion=getAsignaturasPeriodos");
    }
    if (tipo.equals("generar")) {
        response.sendRedirect("SilaboGenerar/SilaboGenerarControlador.jsp?opcion=generarSilabo");
    }
%>
