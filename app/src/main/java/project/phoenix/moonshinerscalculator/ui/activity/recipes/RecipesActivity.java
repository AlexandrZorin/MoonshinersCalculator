package project.phoenix.moonshinerscalculator.ui.activity.recipes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import project.phoenix.moonshinerscalculator.R;
import project.phoenix.moonshinerscalculator.ui.activity.template.TemplateActivity;

public class RecipesActivity extends TemplateActivity {
    private TextView textViewId;
    private TextView textViewRecipeName;
    private TextView textViewRecipeDescriprion;
    private TextView textViewRecipeText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    private void initViews() {
        textViewId = (TextView) findViewById(R.id.recipes_id);
        textViewRecipeName = (TextView) findViewById(R.id.recipes_recipeName);
        textViewRecipeDescriprion = (TextView) findViewById(R.id.recipes_recipeDescription);
        textViewRecipeText = (TextView) findViewById(R.id.recipes_recipeText);
    }
}
