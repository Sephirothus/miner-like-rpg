package com.example.puzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.View;
import android.widget.*;

import java.util.HashMap;

/**
 * Created by sephirothus on 12.02.17.
 */
public class QuestDialog {

    private Context mContext;
    private View mView;
    private int mImgSize;

    QuestDialog(Context context) {
        mContext = context;
        mImgSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 35, mContext.getResources().getDisplayMetrics()
        );
    }

    public void questsClick() {
        ImageView view = (ImageView) ((ExtendActivity) mContext).findViewById(R.id.quests);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }

    public void createDialog() {
        mView = (((ExtendActivity) mContext).getLayoutInflater()).inflate(R.layout.quest_log, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        setQuests();
        builder.setView(mView)
                .setTitle("Quest Log")
                .setCancelable(false)
                .setNegativeButton("Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setQuests() {
        TableLayout table = (TableLayout) mView.findViewById(R.id.quests_list);
        table.removeAllViews();
        Player player = ((ExtendActivity) mContext).mPlayer;
        for (Object questId : player.getQuests()) {
            final String id = questId.toString();
            final HashMap questInfo = player.getQuestById(id);
            TableRow row = new TableRow(mContext);
            TextView title = new TextView(mContext);
            title.setText(questInfo.get("title").toString());
            row.addView(title);
            if (player.isQuestComplete(id)) {
                TextView complete = new TextView(mContext);
                complete.setText("Complete");
                row.addView(complete);
            }
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addQuestInfo(id, questInfo);
                }
            });
            table.addView(row);
        }
    }

    private void addQuestInfo(final String questId, final HashMap questInfo) {
        LinearLayout layout = (LinearLayout) mView.findViewById(R.id.quest_info);
        layout.removeAllViews();
        // title row
        TextView title = new TextView(mContext);
        title.setTextSize(18);
        title.setText(questInfo.get("title").toString());
        layout.addView(title);
        // description row
        TextView desc = new TextView(mContext);
        desc.setText(questInfo.get("description").toString());
        layout.addView(desc);
        // count row
        LinearLayout table = new LinearLayout(mContext);
        table.setOrientation(LinearLayout.HORIZONTAL);
        TextView count = new TextView(mContext);
        ImageView img = new ImageView(mContext);
        Config conf = new Config(mContext);
        String progress = "0";
        switch (Integer.parseInt(questInfo.get("action").toString())) {
            case Quest.QUEST_TYPE_KILL:
                conf.enemyByName(questInfo.get("type").toString());
                img.setImageResource(conf.getCurItemImg());
                progress = questInfo.get("progress_count").toString();
                break;
            case Quest.QUEST_TYPE_GET_ITEM:
                conf.treasureByName(questInfo.get("type").toString());
                img.setImageResource(conf.getCurItemImg());
                progress = String.valueOf(((ExtendActivity) mContext).mPlayer.itemCountInInventory(
                        questInfo.get("type").toString()));
                break;
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mImgSize, mImgSize);
        params.setMargins(0, 0, 20, 0);
        img.setLayoutParams(params);
        count.setText(progress + "/" + questInfo.get("count").toString());
        table.addView(img);
        table.addView(count);
        layout.addView(table);
        // location of unit
        String location = conf.getCurItemLocation();
        if (location != null) {
            TextView getLocation = new TextView(mContext);
            getLocation.setText("Location: " + Config.mPathLocations.get(location));
            layout.addView(getLocation);
        }
        // return row
        TextView returnLocation = new TextView(mContext);
        returnLocation.setText(Quest.getQuestLocation(questId));
        layout.addView(returnLocation);
        // cancel button
        Button button = new Button(mContext);
        button.setText("Cancel quest");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelQuest(questId, questInfo);
            }
        });
        layout.addView(button);
    }

    private void cancelQuest(final String questId, HashMap questInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Cancel quest")
                .setMessage("Are you sure you want to cancel quest - " + questInfo.get("title").toString() + "?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((ExtendActivity) mContext).mPlayer.removeQuest(questId);
                        LinearLayout layout = (LinearLayout) mView.findViewById(R.id.quest_info);
                        layout.removeAllViews();
                        setQuests();
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
