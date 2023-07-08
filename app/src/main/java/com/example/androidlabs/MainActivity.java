import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CharactersAdapter charactersAdapter;
    private ArrayList<Character> characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        characters = new ArrayList<>();
        charactersAdapter = new CharactersAdapter(this, characters);
        listView.setAdapter(charactersAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (findViewById(R.id.frameLayout) != null) {
                    // Tablet mode
                    DetailsFragment detailsFragment = new DetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("character", characters.get(position));
                    detailsFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, detailsFragment)
                            .commit();
                } else {
                    // Phone mode
                    Character character = characters.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("character", character);
                    startActivity(EmptyActivity.newIntent(MainActivity.this, bundle));
                }
            }
        });

        // Start AsyncTask to fetch data from Star Wars API
        new FetchCharactersTask().execute();
    }

    private class FetchCharactersTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://swapi.dev/api/people/?format=json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                String response = scanner.hasNext() ? scanner.next() : "";

                scanner.close();
                inputStream.close();
                connection.disconnect();

                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray results = jsonObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject characterJson = results.getJSONObject(i);
                        String name = characterJson.getString("name");
                        String height = characterJson.getString("height");
                        String mass = characterJson.getString("mass");
                        String hairColor = characterJson.getString("hair_color");
                        String eyeColor = characterJson.getString("eye_color");

                        Character character = new Character(name, height, mass, hairColor, eyeColor);
                        characters.add(character);
                    }

                    charactersAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
