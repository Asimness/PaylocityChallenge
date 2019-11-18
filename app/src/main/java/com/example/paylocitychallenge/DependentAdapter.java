package com.example.paylocitychallenge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This class is the adapter for the dependents for the recycler view.
 */
public class DependentAdapter extends RecyclerView.Adapter<DependentAdapter.DependentViewHolder> {
    private ArrayList<Dependent> dependents; // Data source

    /**
     * View for the dependent list items in the recycler view.
     */
    public class DependentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout dependentLayout;
        public ImageButton deleteDependent;

        public DependentViewHolder(LinearLayout linearLayout) {
            super(linearLayout);
            this.dependentLayout = linearLayout;
            deleteDependent = linearLayout.findViewById(R.id.delete_dependent_button);
            deleteDependent.setOnClickListener(this);
        }

        @Override
        /**
         * Handles deleting dependents when the trashcan icon is clicked.
         */
        public void onClick(View view) {
            removeAt(getAdapterPosition());
            callBack();
        }
    }

    /**
     * This callBack is overrided in the activity class to update the activity when dependents
     * are deleted.
     */
    public void callBack() {}

    /**
     * Removes the dependent at the specified position from the data source and view.
     * @param position of the dependent to be removed
     */
    public void removeAt(int position) {
        dependents.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dependents.size());
    }

    /**
     * Sets the data source for the adapter.
     * @param dependents ArrayList of dependents
     */
    public DependentAdapter(ArrayList<Dependent> dependents) {
        this.dependents = dependents;
    }

    @NonNull
    @Override
    /**
     * Creates dependent views.
     */
    public DependentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dependent_list_item_view, parent, false);
        DependentViewHolder dependentViewHolder = new DependentViewHolder(linearLayout);
        return dependentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DependentViewHolder holder, int position) {
        TextView dependentFirstNameTextView = holder.dependentLayout.findViewById(R.id.dependent_first_name);
        TextView dependentLastNameTextView = holder.dependentLayout.findViewById(R.id.dependent_last_name);
        dependentFirstNameTextView.setText(dependents.get(position).getFirstName());
        dependentLastNameTextView.setText(dependents.get(position).getLastName());
    }

    @Override
    /**
     * Returns the number of dependents in the data source.
     */
    public int getItemCount() {
        return dependents.size();
    }

    /**
     * @return the data source.
     */
    public ArrayList<Dependent> getDependents() {return dependents;}
}
