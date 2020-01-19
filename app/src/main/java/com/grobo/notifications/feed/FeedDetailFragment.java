package com.grobo.notifications.feed;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.transition.TransitionInflater;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grobo.notifications.R;
import com.grobo.notifications.admin.XPortal;
import com.grobo.notifications.feed.addfeed.AddFeedActivity;
import com.grobo.notifications.main.MainActivity;
import com.grobo.notifications.utils.ImageViewerActivity;
import com.grobo.notifications.utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.noties.markwon.Markwon;
import io.noties.markwon.html.HtmlPlugin;
import io.noties.markwon.image.glide.GlideImagesPlugin;

public class FeedDetailFragment extends Fragment {

    public FeedDetailFragment() {
    }

    private FeedItem current;

    public static FeedDetailFragment newInstance() {
        return new FeedDetailFragment();
    }

    private FeedViewModel feedViewModel;
    private FloatingActionButton interestedFab;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);

        context = requireContext();

        setEnterTransition(TransitionInflater.from(context).inflateTransition(android.R.transition.slide_bottom));
        setExitTransition(TransitionInflater.from(context).inflateTransition(android.R.transition.slide_bottom));
        setSharedElementEnterTransition(TransitionInflater.from(context).inflateTransition(R.transition.default_transition));
        setSharedElementReturnTransition(TransitionInflater.from(context).inflateTransition(R.transition.default_transition));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_detail, container, false);

        ImageView eventPoster = view.findViewById(R.id.event_detail_header_bg);
        TextView eventTitle = view.findViewById(R.id.tv_event_detail_title);

        interestedFab = view.findViewById(R.id.fab_event_detail_interested);
        TextView eventVenue = view.findViewById(R.id.tv_event_detail_venue);
        TextView eventTime = view.findViewById(R.id.tv_event_detail_time);
        TextView eventDescription = view.findViewById(R.id.tv_event_detail_description);

        Bundle b = getArguments();

        if (context instanceof MainActivity || context instanceof XPortal) {

            if (b != null) {
                if (b.containsKey("transition_position")) {
                    int transitionPosition = b.getInt("transition_position");
                    eventPoster.setTransitionName("transition_image" + transitionPosition);
                    eventTime.setTransitionName("transition_time" + transitionPosition);
                    eventVenue.setTransitionName("transition_venue" + transitionPosition);
                    eventTitle.setTransitionName("transition_title" + transitionPosition);
                }
                String id = b.getString("id");

                current = feedViewModel.getFeedById(id);

                if (current != null) {

                    Glide.with(this)
                            .load("")
                            .thumbnail(Glide.with(this).load(current.getEventImageUrl()))
                            .centerInside()
                            .placeholder(R.drawable.baseline_dashboard_24)
                            .into(eventPoster);

                    eventTitle.setText(current.getEventName());
                    eventVenue.setText(current.getEventVenue());

                    if (current.getEventDescription() == null) {
                        current.setEventDescription("No Description");
                    }

                    final Markwon markwon = Markwon.builder(context)
                            .usePlugin(GlideImagesPlugin.create(context))
                            .usePlugin(HtmlPlugin.create())
                            .build();

                    final Spanned spanned = markwon.toMarkdown(current.getEventDescription());
                    markwon.setParsedMarkdown(eventDescription, spanned);

                    SimpleDateFormat format = new SimpleDateFormat("dd MMM YYYY, hh:mm a", Locale.getDefault());
                    eventTime.setText(format.format(new Date(current.getEventDate())));

                    if (current.isInterested()) {
                        interestedFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.light_blue)));
                    } else {
                        interestedFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_gray)));
                    }

                    interestedFab.setOnClickListener(v -> toggleStar());

                    if (current.getEventImageUrl() != null && !current.getEventImageUrl().isEmpty()) {
                        eventPoster.setOnClickListener(v -> {
                            Intent i = new Intent(context, ImageViewerActivity.class);
                            i.putExtra("image_url", current.getEventImageUrl());

                            if (getActivity() != null) {
                                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                        getActivity(), eventPoster, "transition");
                                ActivityCompat.startActivity(context, i, activityOptions.toBundle());
                            }
                        });
                    }
                } else {
                    utils.showSimpleAlertDialog(context, "Alert!!!", "Feed not found.");
                    NavHostFragment.findNavController(this).navigateUp();
                    NavHostFragment.findNavController(this).navigate(R.id.nav_feed);
                }
            }

        } else if (context instanceof AddFeedActivity) {

            interestedFab.hide();
            if (b != null) {

                Glide.with(this)
                        .load(b.getString("image"))
                        .centerInside()
                        .placeholder(R.drawable.baseline_dashboard_24)
                        .into(eventPoster);

                eventTitle.setText(b.getString("title"));
                eventVenue.setText(b.getString("venue"));

                final Markwon markwon = Markwon.builder(context)
                        .usePlugin(GlideImagesPlugin.create(context))
                        .usePlugin(HtmlPlugin.create())
                        .build();

                final Spanned spanned = markwon.toMarkdown(b.getString("description", "No Description"));
                markwon.setParsedMarkdown(eventDescription, spanned);

                SimpleDateFormat format = new SimpleDateFormat("dd MMM YYYY, hh:mm a", Locale.getDefault());
                eventTime.setText(format.format(new Date(b.getLong("date"))));
            }
        }

        if (context instanceof XPortal) {

        }

        return view;
    }

    private void toggleStar() {
        if (current.isInterested()) {
            current.setInterested(false);
            interestedFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_gray)));
        } else {
            current.setInterested(true);
            interestedFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.light_blue)));
        }
        feedViewModel.insert(current);
    }

}
