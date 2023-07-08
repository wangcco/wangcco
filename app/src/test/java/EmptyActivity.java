import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class EmptyActivity extends AppCompatActivity {

    public static Intent newIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, EmptyActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new DetailsFragment())
                    .commit();
        }
    }
}

