package com.mayburger.gojekuiclone.ui.base;



 import androidx.databinding.ViewDataBinding;
 import androidx.recyclerview.widget.RecyclerView;

 public class BaseCommonViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {
     private V v;

     public BaseCommonViewHolder(V v) {
         super(v.getRoot());
         this.v = v;
     }

     public V getBinding() {
         return v;
     }
 } 