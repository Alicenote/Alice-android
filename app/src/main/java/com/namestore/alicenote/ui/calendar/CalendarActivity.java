package com.namestore.alicenote.ui.calendar;


import android.app.ProgressDialog;
import android.os.Bundle;

import com.alamkanak.weekview.WeekViewEvent;
import com.namestore.alicenote.models.Event;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example of how events can be fetched from network and be displayed on the week view.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 */
public class CalendarActivity extends CalendarBaseActivity implements Callback<List<Event>> {

    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    boolean calledNetwork = false;
    private AliceApi aliceApi;
    private ProgressDialog prgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Loading");
        prgDialog.show();
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Download events from network if it hasn't been done already. To understand how events are
        // downloaded using retrofit, visit http://square.github.io/retrofit
        aliceApi = ServiceGenerator.creatService(AliceApi.class);
        if (!calledNetwork) {
            aliceApi.getEvent().enqueue(this);
            calledNetwork = true;
        }

        // Return only the events that matches newYear and newMonth.
        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }

    /**
     * Checks if an event falls into a specific year and month.
     *
     * @param event The event to check for.
     * @param year  The year.
     * @param month The month.
     * @return True if the event matches the year and month.
     */
    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year
                && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year
                && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }


    @Override
    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
        if (response.isSuccessful()) {
            prgDialog.dismiss();
            this.events.clear();

            for (int i = 0; i < response.body().size(); i++) {
                this.events.add(response.body().get(i).toWeekViewEvent());
            }
            getWeekView().notifyDatasetChanged();
        }
    }

    @Override
    public void onFailure(Call<List<Event>> call, Throwable t) {
        prgDialog.dismiss();
    }
}
