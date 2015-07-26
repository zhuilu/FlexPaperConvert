package service;

//import Apache HTTP Client v 4.3
//import JSON

public class TestJob {

	public static void main(String[] args) throws Exception {
		int jobId = 15;
		String apiKey = "84575ef60afbd52dfc98464372745d30b5c06337";
		String endpoint = "https://sandbox.zamzar.com/v1/jobs/" + jobId;

		// Create HTTP client and request object
		CloseableHttpClient httpClient = getHttpClient(apiKey);
		HttpGet request = new HttpGet(endpoint);

		// Make request
		CloseableHttpResponse response = httpClient.execute(request);

		// Extract body from response
		HttpEntity responseContent = response.getEntity();
		String result = EntityUtils.toString(responseContent, "UTF-8");

		// Parse result as JSON
		JSONObject json = new JSONObject(result);

		// Print result
		System.out.println(json);

		// Finalise response and client
		response.close();
		httpClient.close();
	}

	// Creates a HTTP client object that always makes requests
	// that are signed with the specified API key via Basic Auth
	private static CloseableHttpClient getHttpClient(String apiKey) {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials(apiKey, ""));

		CloseableHttpClient httpClient = HttpClientBuilder.create()
				.setDefaultCredentialsProvider(credentialsProvider).build();

		return httpClient;
	}
}