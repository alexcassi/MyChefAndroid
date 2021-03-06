package adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.miky.mychef.R;

import entità.Ricetta;

public class RicetteArrayAdapter extends ArrayAdapter<Ricetta> {

    Context ctx;

    public RicetteArrayAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        ctx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.ricette_single_row, null);
            holder = new ViewHolder();
            holder.nome = (TextView) v.findViewById(R.id.nomeTV);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
        holder.nome.setText(getItem(position).getNome_ricetta());

        return v;
    }

    static class ViewHolder {
        TextView nome;
    }

}