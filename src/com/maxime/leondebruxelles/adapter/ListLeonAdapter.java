package com.maxime.leondebruxelles.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.beans.LeonDeBruxelles;
import com.maxime.leondebruxelles.sort.SortByDistance;
import com.maxime.leondebruxelles.utils.Constantes;
import com.maxime.leondebruxelles.utils.Conversion;

/**
 * Adapter permettant d'afficher la liste des restaurants Léon de Bruxelles.
 * 
 */
public class ListLeonAdapter extends BaseAdapter {

	List<LeonDeBruxelles> lesleons = new ArrayList<LeonDeBruxelles>();
	Context ctx;
	LayoutInflater inflater;

	/**
	 * Constructeur de l'Adapter.
	 * @param context Le contexte de l'activity contenant l'adapter.
	 * @param lesleons
	 */
	public ListLeonAdapter(Context context,List<LeonDeBruxelles> lesleons) {
		inflater = LayoutInflater.from(context);
		Collections.sort(lesleons, new SortByDistance());
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

	 public void updateLeonList(List<LeonDeBruxelles> newlist) {
	 	 lesleons = newlist;
	 	 Collections.sort(lesleons, new SortByDistance());
	 	 this.notifyDataSetChanged();
	}
	
	private class ViewHolder {
		TextView LeonId;
		TextView LeonNom;
		TextView Distance;
		TextView Adresse;
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
			holder.Adresse = (TextView)convertView.findViewById(R.id.adapter_leon_adresse);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		/** 
		 * Verification de la distance.
		 * Si la distance est égal à zéro, le GPS n'est pas pas activé.
		 * **/
		if(lesleons.get(position).getDistanceMeterFromUser() == 0)
			holder.Distance.setText("");
		else if(lesleons.get(position).getDistanceMeterFromUser() > Constantes.UN_KILOMETRE)
			holder.Distance.setText(Conversion.metreToKm(lesleons.get(position).getDistanceMeterFromUser())+" Km");
		else if(lesleons.get(position).getDistanceMeterFromUser() < Constantes.UN_KILOMETRE)
			holder.Distance.setText(Conversion.metreToKm(lesleons.get(position).getDistanceMeterFromUser())+" Métres");
		else
			holder.Distance.setText("Impossible de calculer la distance");
		
		holder.LeonId.setText(String.valueOf(lesleons.get(position).getId()));
		holder.LeonNom.setText(lesleons.get(position).getNom());
		holder.Adresse.setText(lesleons.get(position).getAdresse());
		return convertView;
	}
}
