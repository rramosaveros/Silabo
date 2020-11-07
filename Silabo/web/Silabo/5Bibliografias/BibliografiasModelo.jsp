<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.bibliografias.iu.BibliografiasIU"%>
<%@page import="com.google.gson.Gson"%>

<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        String opcion = request.getParameter("opcion");
        try {
            Gson G = new Gson();
            if (opcion.equals("getBibliografias")) {
                String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
                Silabo silabo1 = G.fromJson(jsonAsignaturaInfo, Silabo.class);
                String jsonSilabo = (String) session.getAttribute("jsonSilabo");
                Silabo silabo2 = G.fromJson(jsonSilabo, Silabo.class);
                silabo1.setIdSilabo(silabo2.getIdSilabo());
                silabo1.setRol(silabo2.getRol());
                silabo1.setTipo(silabo2.getTipo());
                silabo1.setIdTipo(silabo2.getIdTipo());
                String jsonBibliografias = port.bibliografiasCargar(G.toJson(silabo1));
                BibliografiasIU bibliografias = G.fromJson(jsonBibliografias, BibliografiasIU.class);
                session.setAttribute("Bibliografias", bibliografias);
                response.sendRedirect("BibliografiasControlador.jsp?opcion=show&accion=view");
            }
            if (opcion.equals("saveBibliografias")) {
                String jsonMenuSilabo = "";
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonBibliografias = (String) session.getAttribute("jsonBibliografias");
                String resultado = port.bibliografiasGuardar(jsonBibliografias);
                 if (resultado.equals("ok")) {
                   BibliografiasIU bibliografias = G.fromJson(jsonBibliografias, BibliografiasIU.class);
                resultado = port.bibliografiasCargar(G.toJson(bibliografias.getSilabos()));
            } else {
                response.sendError(300, "Error al guardar");
            }
                BibliografiasIU bibliografias = G.fromJson(resultado, BibliografiasIU.class);
                session.setAttribute("Bibliografias", bibliografias);
                String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
                MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
                session.setAttribute("MenuLateral", menulateral);
                response.sendRedirect("BibliografiasControlador.jsp?opcion=show&accion=save");
            }
            if (opcion.equals("deleteBibliografia")) {
                String jsonDBibliografias = request.getParameter("jsonDBibliografias");
                String jsonBibliografias = port.bibliografiasEliminar(jsonDBibliografias);
                BibliografiasIU bibliografias = G.fromJson(jsonBibliografias, BibliografiasIU.class);
                session.setAttribute("Bibliografias", bibliografias);
                response.sendRedirect("BibliografiasControlador.jsp?opcion=show&accion=delete");
            }
        } catch (Exception e) {
            Logger.getLogger("BibliografiasModelo.jsp").log(Level.SEVERE, "Silabo/5Bibliografias/BibliografiasModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }
%>
