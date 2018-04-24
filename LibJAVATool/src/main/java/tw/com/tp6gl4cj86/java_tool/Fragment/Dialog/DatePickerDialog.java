package tw.com.tp6gl4cj86.java_tool.Fragment.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.text.DecimalFormat;
import java.util.Calendar;

import tw.com.tp6gl4cj86.java_tool.R;

/**
 * Created by tp6gl4cj86 on 15/2/4.
 */
public class DatePickerDialog extends OlisDialogFragment implements android.app.DatePickerDialog.OnDateSetListener
{

    private int               year               = -1;
    private int               month              = -1;
    private int               day                = -1;
    private OnDateSetListener mOnDateSetListener = null;

    private boolean isSpinner = false;

    public interface OnDateSetListener
    {
        void onDateSet(DatePicker view, int year, int month, int day, String date);
    }

    public static void showInstance(Activity activity, String date, OnDateSetListener mOnDateSetListener)
    {
        showInstance(activity, date, mOnDateSetListener, false);
    }

    /**
     * @param date yyyy-MM-dd HH:mm:ss or yyyy-MM-dd
     */
    public static void showInstance(Activity activity, String date, OnDateSetListener mOnDateSetListener, boolean isSpinner)
    {
        int year, month, day;

        /// [init date]
        if (date.contains(" "))
        {
            date = date.split(" ")[0];
        }
        if (date.length() != 10)
        {
            date = "";
        }
        /// ~[init date Done]

        if (date.equals(""))
        {
            year = month = day = -1;
        }
        else
        {
            year = Integer.parseInt(date.split("-")[0]);
            month = Integer.parseInt(date.split("-")[1]);
            day = Integer.parseInt(date.split("-")[2]);
        }

        showInstance(activity, year, month, day, mOnDateSetListener, isSpinner);
    }

    public static void showInstance(Activity activity, int year, int month, int day, OnDateSetListener mOnDateSetListener)
    {
        showInstance(activity, year, month, day, mOnDateSetListener, false);
    }

    public static void showInstance(Activity activity, int year, int month, int day, OnDateSetListener mOnDateSetListener, boolean isSpinner)
    {
        final Bundle bundle = new Bundle();
        bundle.putInt("year", year);
        bundle.putInt("month", month - 1);
        bundle.putInt("day", day);
        bundle.putBoolean("isSpinner", isSpinner);

        final DatePickerDialog dialog = new DatePickerDialog();
        dialog.setArguments(bundle);
        dialog.mOnDateSetListener = mOnDateSetListener;

        dialog.show(activity, DatePickerDialog.class.getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            year = getArguments().getInt("year");
            month = getArguments().getInt("month");
            day = getArguments().getInt("day");
            isSpinner = getArguments().getBoolean("isSpinner", false);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        if (year < 0 || month < 0 || day < 0)
        {
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }

        if (isSpinner)
        {
            final View view = LayoutInflater.from(getActivity())
                                            .inflate(R.layout.layout_datepicker, null, false);
            final DatePicker datePicker = view.findViewById(R.id.DatePicker);
            datePicker.updateDate(year, month, day);
            return new AlertDialog.Builder(getActivity()).setView(view)
                                                         .setPositiveButton("確定", new DialogInterface.OnClickListener()
                                                         {
                                                             @Override
                                                             public void onClick(DialogInterface dialog1, int which)
                                                             {
                                                                 if (mOnDateSetListener != null)
                                                                 {
                                                                     mOnDateSetListener.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth(), new DecimalFormat("0000").format(datePicker.getYear()) + "-" + new DecimalFormat("00").format(datePicker.getMonth() + 1) + "-" + new DecimalFormat("00").format(datePicker.getDayOfMonth()));
                                                                 }
                                                             }
                                                         })
                                                         .setNegativeButton("取消", null)
                                                         .show();
            //            return new DatePickerDialog(getActivity(), R.style.DatePickerDialogStyle, this, year, month, day);
        }
        else
        {
            return new android.app.DatePickerDialog(getActivity(), this, year, month, day);
        }
    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        if (mOnDateSetListener != null)
        {
            month = month + 1;
            mOnDateSetListener.onDateSet(view, year, month, day, new DecimalFormat("0000").format(year) + "-" + new DecimalFormat("00").format(month) + "-" + new DecimalFormat("00").format(day));
        }
    }

}