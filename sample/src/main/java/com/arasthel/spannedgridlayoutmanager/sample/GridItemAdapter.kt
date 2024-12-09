package com.arasthel.spannedgridlayoutmanager.sample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Jorge Martín on 24/5/17.
 */
class GridItemAdapter: RecyclerView.Adapter<GridItemViewHolder>() {

    val clickedItems: MutableList<Boolean>

    init {
        clickedItems = MutableList(itemCount, { false })
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.MAGENTA, Color.YELLOW)

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {

        holder.positionText.text = "$position"

        holder.layout.setBackgroundColor(
                colors[position % colors.size]
        )

        holder.layout.setOnClickListener {
            clickedItems[position] = !clickedItems[position]
            if (clickedItems[position]) {
                holder.layout.layoutParams.width *= 2
                holder.layout.layoutParams.height *= 2
            } else {
                holder.layout.layoutParams.width = 225
                holder.layout.layoutParams.height = 296
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return 500
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        val gridItemView = LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, parent, false)
        return GridItemViewHolder(gridItemView)
    }
}

open class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open val positionText: AppCompatTextView = itemView.findViewById(R.id.text)
    open val layout: LinearLayout = itemView.findViewById(R.id.layout)
}