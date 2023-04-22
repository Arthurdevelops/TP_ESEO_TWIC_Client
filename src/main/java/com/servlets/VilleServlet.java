package com.servlets;

import com.beans.Ville;
import com.services.VilleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class VilleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VilleService villeService = new VilleService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
            List<Ville> villes = villeService.getAllVilles();
        	request.setAttribute("villes", villes);
            this.getServletContext().getRequestDispatcher("/WEB-INF/villes.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }	
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("calculer") != null) {
            String villeDepart = request.getParameter("villeDepart");
            String villeArrivee = request.getParameter("villeArrivee");
            double latitudeDepart = villeService.getLatitude(villeDepart);
            double latitudeArrivee = villeService.getLatitude(villeArrivee);
            double longitudeDepart = villeService.getLongitude(villeDepart);
            double longitudeArrivee = villeService.getLongitude(villeDepart);

            //Calcul de la distance entre deux villes à partir de leurs données géographiques -> formule de Haversine
            double distance = distance(latitudeDepart,longitudeDepart,latitudeArrivee,longitudeArrivee);
            System.out.println(distance);

            request.setAttribute("distance", distance);
            request.setAttribute("villeArrivee", villeArrivee);
            request.setAttribute("villeDepart", villeDepart);
            try {
                request.setAttribute("villes", villeService.getAllVilles());
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/villes.jsp").forward(request, response);

        } else if (request.getParameter("page2") != null) {
			try {
				List<Ville> villes = villeService.getAllVilles();
	        	request.setAttribute("villes", villes);
			} catch (Exception e) {
				e.printStackTrace();
			}
            this.getServletContext().getRequestDispatcher("/WEB-INF/modifVille.jsp").forward(request, response);
            
        } else if (request.getParameter("page1") != null) {
        	response.sendRedirect("villes");
        }
    }

    
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double r = 6371; // Rayon de la terre en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(lat1)) 
                   * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = r * c;
        return distance;
    }

}


