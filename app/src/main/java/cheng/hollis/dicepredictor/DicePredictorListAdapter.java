package cheng.hollis.dicepredictor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DicePredictorListAdapter extends BaseAdapter {
    private Context cxt;
    private LayoutInflater inflater;
    private List<DiceObject> info = new ArrayList<>();

    public DicePredictorListAdapter(Context Activity, List<DiceObject> info) {
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

    private class ViewHolder {
        TextView TVDice1;
        TextView TVDice2;
        TextView TVDice3;
        TextView TVDice4;
        TextView TVDice5;
        TextView TVDice6;
        TextView TVDice7;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.dice_predictor, null);

        if (convertView != null) {

            TextView TVDice1 = (TextView) convertView.findViewById(R.id.TVDice1);
            TextView TVDice2 = (TextView) convertView.findViewById(R.id.TVDice2);
            TextView TVDice3 = (TextView) convertView.findViewById(R.id.TVDice3);
            TextView TVDice4 = (TextView) convertView.findViewById(R.id.TVDice4);
            TextView TVDice5 = (TextView) convertView.findViewById(R.id.TVDice5);
            TextView TVDice6 = (TextView) convertView.findViewById(R.id.TVDice6);
            TextView TVDice7 = (TextView) convertView.findViewById(R.id.TVDice7);


            TVDice1.setText(info.get(position).getDiceNumber() + "");
            TVDice2.setText(info.get(position).getBecomeOne() + "");
            TVDice3.setText(info.get(position).getBecomeTwo() + "");
            TVDice4.setText(info.get(position).getBecomeThree() + "");
            TVDice5.setText(info.get(position).getBecomeFour() + "");
            TVDice6.setText(info.get(position).getBecomeFive() + "");
            TVDice7.setText(info.get(position).getBecomeSix() + "");
        }

        return convertView;
    }
}