package com.grobo.notifications.admin.clubevents;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grobo.notifications.feed.FeedViewModel;

public class ClubEventDetailFragment extends Fragment {

    public ClubEventDetailFragment() {
    }

    public static ClubEventDetailFragment newInstance() {
        return new ClubEventDetailFragment();
    }

    private FeedViewModel feedViewModel;
    private FloatingActionButton interestedFab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_feed_detail, container, false);

//        ImageView eventPoster = view.findViewById(R.id.event_detail_header_bg);
//        TextView eventTitle = view.findViewById(R.id.tv_event_detail_title);
//
//        interestedFab = view.findViewById(R.id.fab_event_detail_interested);
//        TextView eventVenue = view.findViewById(R.id.tv_event_detail_venue);
//        TextView eventTime = view.findViewById(R.id.tv_event_detail_time);
//        TextView eventDescription = view.findViewById(R.id.tv_event_detail_description);
//
//        Bundle b = getArguments();

//        if (getContext() instanceof MainActivity || getContext() instanceof XPortal) {
//
//            if (b != null) {
//                String transitionName = b.getString("transitionName");
//                eventPoster.setTransitionName(transitionName);
//                String id = b.getString("id");
//
//                final ClubEventItem current = feedViewModel.getFeedById(id);
//
//                Glide.with(this)
//                        .load(current.getEventImageUrl())
//                        .centerInside()
//                        .placeholder(R.drawable.baseline_dashboard_24)
//                        .into(eventPoster);
//
//                eventTitle.setText(current.getEventName());
//                eventVenue.setText(current.getEventVenue());
//
//                if (current.getEventDescription() == null) {
//                    current.setEventDescription("No Description");
//                }
//                final Markwon markwon = Markwon.builder(getContext())
//                        .usePlugin(ImagesPlugin.create(getContext())).build();
//                final Spanned spanned = markwon.toMarkdown(current.getEventDescription());
//                markwon.setParsedMarkdown(eventDescription, spanned);
//
//                SimpleDateFormat format = new SimpleDateFormat("dd MMM YYYY, hh:mm a");
//                eventTime.setText(format.format(new Date(current.getEventDate())));
//
//                if (current.isInterested()) {
//                    interestedFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.light_blue)));
//                } else {
//                    interestedFab.getBackground().setTint(getResources().getColor(R.color.dark_gray));
//                }
//
//                interestedFab.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        toggleStar(current);
//                    }
//                });
//
//            }
//
//        } else if (getContext() instanceof AddFeedActivity) {
//
//            interestedFab.hide();
//            if (b != null) {
//
//                Glide.with(this)
//                        .load(b.getString("image"))
//                        .centerInside()
//                        .placeholder(R.drawable.baseline_dashboard_24)
//                        .into(eventPoster);
//
//                eventTitle.setText(b.getString("title"));
//                eventVenue.setText(b.getString("venue"));
//
//                final Markwon markwon = Markwon.builder(getContext())
//                        .usePlugin(ImagesPlugin.create(getContext())).build();
//                final Spanned spanned = markwon.toMarkdown(b.getString("description", "No Description"));
//                markwon.setParsedMarkdown(eventDescription, spanned);
//
//                SimpleDateFormat format = new SimpleDateFormat("dd MMM YYYY, hh:mm a");
//                eventTime.setText(format.format(new Date(b.getLong("date"))));
//            }
//        }
//
//        if (getContext() instanceof XPortal) {
//
//            List<String> itemsList = Converters.arrayFromString(PreferenceManager
//                    .getDefaultSharedPreferences(getContext()).getString(USER_POR, ""));
//
//            if (itemsList != null && itemsList.size() != 0)
//
//            for (String por : itemsList) {
//                String[] array = por.split("_", 2);
//                String club = array[1];
//            }
//
//            //TODO : implement feed edit and delete functionality
//        }

        return null;
    }
//
//    private void toggleStar(ClubEventItem item) {
//        if (item.isInterested()) {
//            item.setInterested(false);
//        } else {
//            item.setInterested(true);
//        }
//        feedViewModel.insert(item);
//
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .detach(this)
//                .attach(this)
//                .commit();
//    }
//

}
