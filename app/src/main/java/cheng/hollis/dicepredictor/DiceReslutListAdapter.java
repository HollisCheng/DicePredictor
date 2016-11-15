package cheng.hollis.dicepredictor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DiceReslutListAdapter extends BaseAdapter {
    private Context cxt;
    private LayoutInflater inflater;
    private List<DiceResult> info = new ArrayList<>();

    public DiceReslutListAdapter(Context Activity, List<DiceResult> info) {
        this.cxt = Activity;
        this.info = info;
        this.inflater = (LayoutInflater) Activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public Object getItem(int position) {
        return info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.dice_result, null);

        if (convertView != null) {
            TextView TVDice1 = (TextView) convertView.findViewById(R.id.TVDice1);
            TextView TVDice2 = (TextView) convertView.findViewById(R.id.TVDice2);
            TextView TVDice3 = (TextView) convertView.findViewById(R.id.TVDice3);

            TVDice1.setText(info.get(position).getThisRd1()+"");
            TVDice2.setText(info.get(position).getThisRd2()+"");
            TVDice3.setText(info.get(position).getThisRd3()+"");
        }
        return convertView;
    }
}