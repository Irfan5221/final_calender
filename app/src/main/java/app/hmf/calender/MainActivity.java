package app.hmf.calender;

import static app.hmf.calender.CalendarUtils.monthYearFromDate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private TextView monthYearText;
    ImageView prev_month, next_month;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE ); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags( WindowManager.LayoutParams.ALPHA_CHANGED );
            window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
            window.setStatusBarColor( this.getResources().getColor( R.color.white ) );
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR );
        }
        setContentView( R.layout.activity_main );
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        monthYearText = findViewById( R.id.monthYearTV );
        prev_month = findViewById( R.id.prev_month );
        next_month = findViewById( R.id.next_month );

        prev_month.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths( 1 );
                setMonthView();
            }
        } );

        next_month.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths( 1 );
                setMonthView();
            }
        } );
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText( monthYearFromDate( CalendarUtils.selectedDate ) );

    }


}

