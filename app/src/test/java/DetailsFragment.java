import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.Objects;

public class DetailsFragment extends Fragment {

    private TextView nameTextView;
    private TextView heightTextView;
    private TextView massTextView;
    private TextView hairColorTextView;
    private TextView eyeColorTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        nameTextView = view.findViewById(R.id.nameTextView);
        heightTextView = view.findViewById(R.id.heightTextView);
        massTextView = view.findViewById(R.id.massTextView);
        hairColorTextView = view.findViewById(R.id.hairColorTextView);
        eyeColorTextView = view.findViewById(R.id.eyeColorTextView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Character character = (Character) bundle.getSerializable("character");
            if (character != null) {
                nameTextView.setText(character.getName());
                heightTextView.setText(character.getHeight());
                massTextView.setText(character.getMass());
                hairColorTextView.setText(character.getHairColor());
                eyeColorTextView.setText(character.getEyeColor());
            }
        }

        return view;
    }
}

