package com.example.lzq.supperpictagview.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lzq.supperpictagview.R;


public class CustomDialogLoading extends Dialog {

	private static String loading;
	public CustomDialogLoading(Context context, int theme) {
		super(context, theme);
		
	}

	public CustomDialogLoading(Context context) {
		super(context);
		
	}

	/**
	 * Helper class for creating a custom dialog
	 */
	public static class Builder {

		private Context context;

		private View contentView;

		public Builder(Context context, String text) {
			this.context = context;
			CustomDialogLoading.loading = text;
		}

		/**
		 * Set a custom content view for the Dialog. If a message is set, the
		 * contentView is not added to the Dialog...
		 * 
		 * @param v
		 * @return
		 */
		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * Create the custom dialog
		 */
		public CustomDialogLoading create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			// instantiate the dialog with the custom Theme
			final CustomDialogLoading dialog = new CustomDialogLoading(context,
					R.style.Dialog);

			dialog.setCanceledOnTouchOutside(false);
			View layout = inflater
					.inflate(R.layout.custom_dialog_loading, null);

			ImageView mIma = (ImageView) layout.findViewById(R.id.ima);
			TextView loding = (TextView) layout.findViewById(R.id.tv_loding_text);
			//loding.setText(CustomDialogLoading.loading);
			Animation anim = AnimationUtils.loadAnimation(context,
					R.anim.dialog_loding_rotate);

			/*
			 * 开始和停止旋转 在操作开始之前调用
			 */

			if (anim != null) {
				mIma.startAnimation(anim);
			}

			LinearInterpolator lir = new LinearInterpolator();
			anim.setInterpolator(lir);

			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

			if (contentView != null) {

				((LinearLayout) layout.findViewById(R.id.content))
						.removeAllViews();

				((LinearLayout) layout.findViewById(R.id.content)).addView(
						contentView, new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
			}

			dialog.setContentView(layout);
			return dialog;
		}

	}

}