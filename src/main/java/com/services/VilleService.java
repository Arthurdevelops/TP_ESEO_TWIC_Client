package com.services;

import com.beans.Ville;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VilleService {
	private static final String API_URL_villes = "http://localhost:8081/villes";
	private static final String API_URL_villeByName = "http://localhost:8081/villes";

	public List<Ville> getAllVilles() throws Exception {
		List<Ville> villes = new ArrayList<>();
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet(API_URL_villes);
			CloseableHttpResponse response = client.execute(request);
			String json = EntityUtils.toString(response.getEntity());
			JSONArray jsonArray = new JSONArray(json);
			//System.out.println(json);
			//System.out.println(jsonArray);
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Ville ville = new Ville();
				//Vérification du JSON
				if(jsonObject.isNull("codeCommune")) {
					ville.setCodeCommune("Pas d'information");
				} else {
					ville.setCodeCommune(jsonObject.getString("codeCommune"));
				}
				if(jsonObject.isNull("nom")) {
					ville.setNomCommune("Pas d'information");
				} else {
					ville.setNomCommune(jsonObject.getString("nom"));
				}
				if(jsonObject.isNull("codePostal")) {
					ville.setCodePostale("Pas d'information");
				} else {
					ville.setCodePostale(jsonObject.getString("codePostal"));
				}
				if(jsonObject.isNull("latitude")) {
					ville.setLatitude("Pas d'information");
				} else {
					ville.setLatitude(jsonObject.getString("latitude"));
				}
				if(jsonObject.isNull("longitude")) {
					ville.setLongitude("Pas d'information");
				} else {
					ville.setLongitude(jsonObject.getString("longitude"));
				}
				villes.add(ville);
			}
		}
		return villes;
	}

	public double getLatitude(String villeDepart) throws IOException {
		double latitude = 0;
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet(API_URL_villeByName + "/" + villeDepart);
			CloseableHttpResponse response = client.execute(request);
			String json = EntityUtils.toString(response.getEntity());
			Object obj = new JSONTokener(json).nextValue();
			if (obj instanceof JSONObject) {
			    latitude = Float.parseFloat(((JSONObject)obj).getString("latitude"));
			}
		}
		return latitude;
	}

	public double getLongitude(String villeDepart) throws IOException {
		double longitude = 0;
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet(API_URL_villeByName + "/" + villeDepart);
			CloseableHttpResponse response = client.execute(request);
			String json = EntityUtils.toString(response.getEntity());
			Object obj = new JSONTokener(json).nextValue();
			if (obj instanceof JSONObject) {
				longitude = Float.parseFloat(((JSONObject)obj).getString("longitude"));
			}
		}
		return longitude;
	}
	



}
