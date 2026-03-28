package com.carelink.app.ui.notes;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.carelink.app.databinding.FragmentNotesBinding;
import com.carelink.app.utils.DemoDataHelper;
import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment implements AddNoteDialogFragment.NoteSavedListener {
    private FragmentNotesBinding binding;
    private final List<String> localNotes = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        localNotes.clear();
        localNotes.addAll(DemoDataHelper.getNotes());
        renderNotes();
        binding.fabAddNote.setOnClickListener(v -> new AddNoteDialogFragment().show(getChildFragmentManager(), "add_note"));
    }

    private void renderNotes() {
        if (!(binding.rvNotes.getParent() instanceof LinearLayout)) return;
        LinearLayout parent = (LinearLayout) binding.rvNotes.getParent();
        int recyclerIndex = parent.indexOfChild(binding.rvNotes);
        if (recyclerIndex >= 0) parent.removeView(binding.rvNotes);

        LinearLayout notesContainer = new LinearLayout(requireContext());
        notesContainer.setOrientation(LinearLayout.VERTICAL);
        notesContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));

        for (String note : localNotes) {
            LinearLayout card = new LinearLayout(requireContext());
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(24, 22, 24, 22);
            card.setBackground(createRoundedBg(0xFFFFFFFF, 24));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.bottomMargin = 16;
            card.setLayoutParams(params);

            TextView title = new TextView(requireContext());
            title.setText("照护备注");
            title.setTextSize(14);
            title.setPadding(18, 10, 18, 10);
            title.setBackground(createRoundedBg(0xFFE3F2FD, 18));
            card.addView(title);

            TextView item = new TextView(requireContext());
            item.setText(note);
            item.setTextSize(17);
            item.setTypeface(Typeface.DEFAULT_BOLD);
            item.setPadding(0, 14, 0, 0);
            card.addView(item);
            notesContainer.addView(card);
        }
        parent.addView(notesContainer, recyclerIndex >= 0 ? recyclerIndex : 1);
    }

    @Override
    public void onNoteSaved(String noteContent) {
        localNotes.add(0, noteContent + "\n—— 家属新增");
        renderNotes();
    }

    private GradientDrawable createRoundedBg(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
