/**
 * GameListAdapter.java: Adapter class to display games in list view
 */
package edu.miracostacollege.stattracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.miracostacollege.stattracker.model.Game;

public class GameListAdapter extends ArrayAdapter<Game>
{
    /** Instance variables */
    private Context context;
    private List<Game> gamesList;
    private int resourceID;

    /**
     * Constructor
     *
     * @param c
     * @param id
     * @param games
     */
    public GameListAdapter(Context c, int id, List<Game> games)
    {
        super(c, id, games);
        context = c;
        resourceID = id;
        gamesList = games;
    }

    /**
     * GetView method to populate list
     *
     * @param pos
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        Game selectedGame = gamesList.get(pos);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resourceID, null);

        LinearLayout gameListLinearLayout = view.findViewById(R.id.gameListLinearLayout);
        gameListLinearLayout.setTag(selectedGame);

        TextView gameListOpponentTextView = view.findViewById(R.id.gameListOpponentTextView);
        TextView gameListDateTextView = view.findViewById(R.id.gameListDateTextView);

        gameListOpponentTextView.setText("Opponent: " + selectedGame.getOpponent());
        gameListDateTextView.setText(selectedGame.getDate());

        return view;
    }
}
