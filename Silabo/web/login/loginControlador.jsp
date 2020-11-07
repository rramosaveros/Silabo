<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String usr = request.getParameter("ddaCuenta");
    String pass = request.getParameter("ddaClave");
    String opcion = request.getParameter("opcion");
    String url = "";
    //verificar cambios con git
    // nuevos cambios de git 
    // un nodo haciendo cambios para git 
//    if (request.getUserPrincipal() != null) {
//        AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
//        final Map attributes = principal.getAttributes();
//        if (attributes != null) {
//            Iterator attributeNames = attributes.keySet().iterator();
//            if (attributeNames.hasNext()) {
//                for (; attributeNames.hasNext();) {
//                    String attributeName = (String) attributeNames.next();
//                    final Object attributeValue = attributes.get(attributeName);
//                    if (attributeValue instanceof List) {
//                        final List values = (List) attributeValue;
//                    } else {
//                        if (attributeName == "cedula") {
//                            usr = attributeValue.toString();
//                            break;
//                        }
//                    }
//                }
//            } else {
//                out.println("<pre>The attribute map is empty. Review your CAS filter configurations.</pre>");
//            }
//        } else {
//            out.println("<pre>The user principal is empty from the request object. Review the wrapper filter configuration.</pre>");
//        }
//    }
//    if (usr != null) {
//        String usrP = usr.substring(0, 9);
//        String usrS = usr.substring(9, 10);
//        usr = usrP + "-" + usrS;
//    }
    if (logueo == null && opcion == null && usr != null) {
        url = "loginModelo.jsp";
        session.setAttribute("cuenta", usr);
        session.setAttribute("clave", pass);
        response.sendRedirect(url);
    } else if (logueo != null && opcion == null) {
        response.sendRedirect("../index.jsp");
    } else if (opcion != null && logueo != null) {
        session.setAttribute("logueo", null);
        session.invalidate();
//        request.getSession().removeAttribute("logueo");
//        request.getSession().invalidate();
        url = ("https://login.microsoftonline.com/common/oauth2/logout?post_logout_redirect_uri=https%3A%2F%2Fseguridad.espoch.edu.ec%2Fcas%2Flogout%3Fservice%3Dhttps%3A%2F%2Fsilabos.espoch.edu.ec:8181%2FSilabo%2F");
//        response.sendRedirect("http://dda.espoch.edu.ec/Menu");
//        url = ("https://seguridad.espoch.edu.ec/cas/login?service=http%3A%2F%2Fpruebas.espoch.edu.ec:8080%2FSilabo%2F");
//        url = ("https://seguridad.espoch.edu.ec/cas/login?service=http%3A%2F%2F172.17.102.69:8080%2FSilabo%2F");
        response.sendRedirect(url);
    } else {
//        url = ("https://seguridad.espoch.edu.ec/cas/login?service=http%3A%2F%2Fpruebas.espoch.edu.ec:8080%2FSilabo%2F");
//        url = ("https://seguridad.espoch.edu.ec/cas/login?service=http%3A%2F%2F172.17.102.69:8080%2FSilabo%2F"); //Preproduccion
//        url = ("https://seguridad.espoch.edu.ec/cas/login?service=https%3A%2F%2Fsilabos.espoch.edu.ec:8181%2FSilabo%2F"); //Preproduccion2
        url = "loginVista.html";
        response.sendRedirect(url);
    }
%>