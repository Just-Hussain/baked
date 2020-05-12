package com.example.baked;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.baked.models.Recipe;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    static String[] datax = {"fdfdaf", "SDFVqef", "sdv wfwef", "adf e3"};
    static Recipe data;
    public static void setData(Recipe data) {
        RecipeWidgetProvider.data = data;
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        views.setTextViewText(R.id.tv_widget_header, data.getName());

        for (int i = 0; i < data.getIngredients().size(); i++) {
            RemoteViews newView = new RemoteViews(context.getPackageName(), R.layout.widget_iteam);
            newView.setTextViewText(R.id.tv_widget_item, data.getIngredients().get(i).getIngredient());
            views.addView(R.id.ll_widget_ingredients, newView);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

