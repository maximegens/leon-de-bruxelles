package com.maxime.leondebruxelles.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;


/**
 * Adaptateur permettant de mettre en forme et d'afficher chaque item, dans un style personnalisÃ©, de la listeView pour la page d'accueil.
 *
 */
public class ListLeonAdapter extends BaseAdapter {

	List<LeonDeBruxelles> lesleons = new ArrayList<LeonDeBruxelles>();
	Context ctx;
	LayoutInflater inflater;

	public ListLeonAdapter(Context context,List<LeonDeBruxelles> lesleons) {
		inflater = LayoutInflater.from(context);
		this.lesleons = lesleons;
		ctx = context;
		
	}
	@Override
	public int getCount() {
		return lesleons.size();
	}

	@Override
	public Object getItem(int position) {
	
		return lesleons.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		TextView LeonId;
		TextView LeonNom;
		TextView Distance;

	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.leon_item, null);

			holder.LeonId = (TextView)convertView.findViewById(R.id.adapter_leon_id);
			holder.LeonNom = (TextView)convertView.findViewById(R.id.adapter_leon_nom);
			holder.Distance = (TextView)convertView.findViewById(R.id.adapter_leon_distance);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.LeonId.setText(String.valueOf(lesleons.get(position).getId()));
		holder.LeonNom.setText(lesleons.get(position).getNom());
		holder.Distance.setText("Calcul en cours");
		return convertView;
	}
}
