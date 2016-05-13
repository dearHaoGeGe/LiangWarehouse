package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.sql.DaoSingleton;
import com.dream.myliu.liangwarehouse.greendao.UserGoodsCartEty;
import com.dream.myliu.liangwarehouse.view.NumberAddSubView;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Amethyst on 16/1/23/09/23.
 * <p/>
 * 购物车列表显示
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private CheckBox checkBox;
    private TextView textView;
    private ImageLoader imageLoader;

    private List<UserGoodsCartEty> datas;
    private Context context;

    public void addData(List<UserGoodsCartEty> userGoodsCartEties) {
        this.datas = userGoodsCartEties;
        notifyDataSetChanged();
//        showTotalPrice();
    }

    //将activity中的CheckBox checkBox, TextView 传递过来进行赋值,再次界面进行操作
    public CartAdapter(Context context, CheckBox checkBox, TextView textView) {
        setCheckBox(checkBox);
        setTextView(textView);
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.context = context;
        imageLoader = VolleySingleton.getInstance().getImageLoader(); //用于完成imageview
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_item, null));
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, int position) {
        holder.text_title.setText(datas.get(position).getGood_name() + "");   //商品名
        holder.text_price.setText(datas.get(position).getOnePrice() + "");   //商品价格
//        holder.num_control.setText(datas.get(position).getCounts() + "");
        holder.drawee_view.setDefaultImageResId(R.mipmap.demo_2);   //商品的图片
        holder.drawee_view.setErrorImageResId(R.mipmap.demo_people);
        holder.position = position;  //设置当前的位置信息,标记 防止checkbox的复选,当再次刷新时不会重复选用
        holder.drawee_view.setImageUrl(datas.get(position).getUrl_img(), imageLoader);
        holder.checkBox.setChecked(datas.get(position).getIsChecked());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //设置当前的checkbox的状态
                datas.get(holder.position).setIsChecked(isChecked);
                //显示总价
                textView.setText(getTotalPrice() + "");
            }
        });

        //物品的增减的设置,并添加监听, 当商品增加和减少时数据库同时进行增减操作
        holder.numberAddSubView.setValue(new Long(datas.get(position).getCounts()).intValue());
        holder.numberAddSubView.setOnButtonClickListener(new NumberAddSubView.OnButtonClickListener() {
            @Override
            public void onButtonAddClick(View view, int//                DaoSingleton.getInstance().insertUserGoodsCartEty(datas);
                    value) {
                datas.get(holder.position).setCounts((long) value);
                DaoSingleton.getInstance().insertUserGoodsCartEty(datas);
                showTotalPrice();
            }

            @Override
            public void onButtonSubClick(View view, int value) {
                datas.get(holder.position).setCounts((long) value);
                //将数据库中的数据进行更新
                DaoSingleton.getInstance().insertUserGoodsCartEty(datas);
                showTotalPrice(); //实时显示价格
            }
        });

        //点击
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener = new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        UserGoodsCartEty cart = datas.get(position);
                        cart.setIsChecked(!cart.getIsChecked());
                        notifyItemChanged(position);
                        checkListen();
                        showTotalPrice();
                    }
                };
            }
        });
        //长按删除
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemLongClickLister = new OnItemLongClickLister() {
                    @Override
                    public void onItemLongClick(View view, int position) {
                        //长按删除
                        DaoSingleton.getInstance().deleteUserGoodsCartEty(datas.get(position));
                        datas.remove(position);
                        notifyItemChanged(position);
                    }
                };
                return false;
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickLister mOnItemLongClickLister = null;

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickLister {
        void onItemLongClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView drawee_view;
        TextView text_title, text_price;
        NumberAddSubView num_control;
        CheckBox checkBox;
        NumberAddSubView numberAddSubView;
        int position;

        public CartViewHolder(View itemView) {
            super(itemView);
            drawee_view = (NetworkImageView) itemView.findViewById(R.id.drawee_view);
            text_price = (TextView) itemView.findViewById(R.id.text_price);
            text_title = (TextView) itemView.findViewById(R.id.text_title);
            num_control = (NumberAddSubView) itemView.findViewById(R.id.num_control);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            numberAddSubView = (NumberAddSubView) itemView.findViewById(R.id.num_control);
        }
    }


    private boolean isNull() {
        return (datas != null && datas.size() > 0);
    }

    private void checkListen() {
        int count = 0;
        int checkNum = 0;
        if (datas != null) {
            count = datas.size();

            for (UserGoodsCartEty cart : datas) {
                if (!cart.getIsChecked()) {
                    checkBox.setChecked(false);
                    break;
                } else {
                    checkNum = checkNum + 1;
                }
            }

            if (count == checkNum) {
                checkBox.setChecked(true);
            }

        }
    }


    public void checkAll_None(boolean isChecked) {
        if (!isNull())
            return;
        int i = 0;
        for (UserGoodsCartEty cart : datas) {
            cart.setIsChecked(isChecked);
            notifyItemChanged(i);
            i++;
        }


    }

    public void showTotalPrice() {
        float total = getTotalPrice();
//        .setText(total +"");
        textView.setText(total + "");
    }


    public float getTotalPrice() {
        float sum = 0;
        if (!isNull())
            return sum;

        for (UserGoodsCartEty cart : datas) {
            if (cart.getIsChecked()) {
                sum += (cart.getCounts() * cart.getOnePrice());
            }
        }
        return sum;
    }


    public void clear() {
//        int itemCount = datas.size();
//        datas.clear();
//        this.notifyItemRangeRemoved(0,itemCount);

        for (Iterator it = datas.iterator(); it.hasNext(); ) {
//
//            T t = (T) it.next();
//            int position = datas.indexOf(t);
//            it.remove();
//            notifyItemRemoved(position);
        }
    }


    public void setTextView(TextView textview) {
        this.textView = textview;
    }

    public void setCheckBox(CheckBox ck) {
        this.checkBox = ck;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAll_None(checkBox.isChecked());
                showTotalPrice(); //每改变一次都会发生重新显示一下当前的值
            }
        });
    }


}
