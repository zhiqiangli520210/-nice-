package com.example.lzq.supperpictagview.utils.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.example.lzq.supperpictagview.R;


/**
 * 下载进度提示框Dialog
 * @author hrcf
 *
 */
public class CustomDialogProgressBar extends ProgressDialog {

	public CustomDialogProgressBar(Context context, int theme) {
		super(context, theme);
	}

	public CustomDialogProgressBar(Context context) {
		super(context);
	}

	/**
	 * Helper class for creating a custom dialog
	 */
	public static class Builder {

		private Context context;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private View contentView;
		private int mMax;

		private DialogInterface.OnClickListener positiveButtonClickListener,
				negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		/**
		 * Set the Dialog message from String
		 *
		 * @param
		 * @return
		 */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 *
		 * @param
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 *
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 *
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
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
		 * Set the positive button resource and it's listener
		 *
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		/*
		 * public Builder setPositiveButton(int positiveButtonText,
		 * DialogInterface.OnClickListener listener) { this.positiveButtonText =
		 * (String) context .getText(positiveButtonText);
		 * this.positiveButtonClickListener = listener; return this; }
		 */

		/**
		 * Set the positive button text and it's listener
		 *
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(String positiveButtonText,
				DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button resource and it's listener
		 *
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		/*
		 * public Builder setNegativeButton(int negativeButtonText,
		 * DialogInterface.OnClickListener listener) { this.negativeButtonText =
		 * (String) context .getText(negativeButtonText);
		 * this.negativeButtonClickListener = listener; return this; }
		 */

		/**
		 * Set the negative button text and it's listener
		 *
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(String negativeButtonText,
				DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}
		
		public void setMax(int max) {
	       
	            this.mMax = max;
	        
	    }

		/**
		 * Create the custom dialog
		 */
		public CustomDialogProgressBar create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			// instantiate the dialog with the custom Theme
			final CustomDialogProgressBar dialog = new CustomDialogProgressBar(context,
					R.style.Dialog);

			dialog.setCanceledOnTouchOutside(false);
			View layout = inflater.inflate(R.layout.custom_dialog_progressbar, null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			// set the dialog title
			((TextView) layout.findViewById(R.id.title)).setText(title);
			
			// set the confirm button
			dialog.setContentView(layout);
			return dialog;
		}

	}

}