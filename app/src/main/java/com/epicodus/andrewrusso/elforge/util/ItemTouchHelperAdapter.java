package com.epicodus.andrewrusso.elforge.util;

/**
 * Created by andrewrusso on 7/26/16.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
