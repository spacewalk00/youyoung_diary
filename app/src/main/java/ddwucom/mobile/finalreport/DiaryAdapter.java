package ddwucom.mobile.finalreport;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {

    final static String TAG = "DiaryAdapter";

    private Context context;
    private int layout;
    private ArrayList<Diary> diaryList;
    private LayoutInflater layoutInflater;

    public DiaryAdapter(Context context, int layout, ArrayList<Diary> diaryList) {
        this.context = context;
        this.layout = layout;
        this.diaryList = diaryList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {  return diaryList.size();  }

    @Override
    public Object getItem(int i) { return diaryList.get(i); }

    @Override
    public long getItemId(int i) { return diaryList.get(i).get_id(); }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(layout, viewGroup, false);
            Log.d(TAG, "Created!!");

            viewHolder = new ViewHolder();
//            viewHolder.feeling = convertView.findViewById(R.id.feeling);
            viewHolder.tvFeeling = convertView.findViewById(R.id.tvFeeling);
            viewHolder.tvDate = convertView.findViewById(R.id.tvDate);
            viewHolder.tvWeather = convertView.findViewById(R.id.tvWeather);
            viewHolder.tvTitle = convertView.findViewById(R.id.tvTitle);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvFeeling.setText(diaryList.get(pos).getFeeling());
        viewHolder.tvDate.setText(diaryList.get(pos).getDate());
        viewHolder.tvWeather.setText(diaryList.get(pos).getWeather());
        viewHolder.tvTitle.setText(diaryList.get(pos).getTitle());

        return convertView;
    }

    static class ViewHolder {
//        ImageView feeling;
        TextView tvFeeling;
        TextView tvDate;
        TextView tvWeather;
        TextView tvTitle;
    }
}
