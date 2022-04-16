package com.module.mynotebook;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

        import androidx.annotation.NonNull;

        import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<Note> courseModalArrayList;
    private Context context;
    View.OnClickListener onClickListener;

    // creating a constructor for our variables.
    public Adapter(ArrayList<Note> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        Note modal = courseModalArrayList.get(position);

        holder.title.setText(modal.getTitle());
        holder.description.setText(modal.getDescription());
        holder.date.setText(modal.getCreateTime());
        Uri uri = Uri.parse(modal.getImageuri());
        holder.imageView.setImageURI(uri);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are Your Sure You Want To Delete ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeItem(position);
                        notifyItemRemoved(position);
                        context.getSharedPreferences("share preferences",position).edit().clear().apply();
                        dialogInterface.cancel();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.title.setText(modal.getTitle());
                holder.description.setText(modal.getDescription());
                holder.date.setText(modal.getCreateTime());
                holder.imageView.setImageURI(uri);


                Intent intent=new Intent(context,InfoReadActivity.class);
                intent.putExtra("title",modal.getTitle());
                intent.putExtra("description",modal.getDescription());
                intent.putExtra("date",modal.getCreateTime());
                intent.putExtra("image",modal.getImageuri());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView title, description,date;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            title = itemView.findViewById(R.id.titletv);
            description = itemView.findViewById(R.id.descriptiontv);
            date=itemView.findViewById(R.id.datetv);
            imageView=itemView.findViewById(R.id.imageviews);
        }
    }
    public void removeItem(int position) {
        courseModalArrayList.remove(position);

        notifyItemRemoved(position);
    }
}
