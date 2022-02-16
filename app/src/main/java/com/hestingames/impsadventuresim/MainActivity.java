package com.hestingames.impsadventuresim;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.hestingames.impsadventuresim.enums.ETarotEffectType;
import com.hestingames.impsadventuresim.enums.StrategyEnum;
import com.hestingames.impsadventuresim.model.Config;
import com.hestingames.impsadventuresim.simulator.Board;
import com.hestingames.impsadventuresim.simulator.SimulationStats;
import com.hestingames.impsadventuresim.simulator.Stats;
import com.hestingames.impsadventuresim.utils.CustomSpinnerAdapter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {
    public EditText starNumber;
    public EditText diceNumber;
    public EditText luckyDiceNumber;
    public EditText position;
    public EditText mushroom1Level;
    public EditText mushroom2Level;
    public EditText mushroom3Level;

    public Switch smartThrow;
    public Switch useNo19;
    public Switch useSmartCard;
    public Switch useKarmaOut;

    public ProgressBar loadingBar;
    public TextView progresLabel;
    public TextView min;
    public TextView max;
    public TextView average;
    public TextView prob80;
    public TextView prob110;
    public TextView prob140;
    public TextView prob170;
    public TextView prob200;
    public TextView prob230;
    public TextView prob260;
    public TextView prob300;
    public TextView versionText;
    public TextView spiritLabel;
    public TextView magicDustLabel;
    public TextView mosterSoulLabel;
    public TextView promotionStoneLabel;
    public TextView chaosStoneLabel;
    public TextView shards3Label;
    public TextView shards4Label;
    public TextView shards5Label;
    public Button simulateBtn;
    public Button stopSimulateBtn;
    public ImageButton configBtn;
    public ImageButton helpBtn;
    public ImageButton donateBtn;
    public ImageButton homeBtn;
    public ImageButton shareBtn;
    public ConstraintLayout informationBlock;
    public ConstraintLayout resultBlock;
    public ConstraintLayout loadingBlock;
    public ConstraintLayout formBlock;
    public LinearLayout helpBlock;
    public ConstraintLayout configBlock;
    public ConstraintLayout cardsBlock;
    public LinearLayout donateBlock;
    public SeekBar configSimBar;
    public TextView configSimBarLabel;
    public Button configSaveBtn;
    public TextView configSimBarAlerLabel;
    public ConstraintLayout resourcesBlock;
    public Spinner spinner;

    public TextView luckyCardValue;
    public TextView powerCardValue;
    public TextView energyCardValue;
    public TextView mushroomCardValue;
    public TextView cloudCardValue;
    public TextView rebirthCardValue;
    public TextView trickCardValue;
    public TextView bilghtCardValue;
    public TextView copycatCardValue;


    public String[] cardsNames;

    public Config config;

    int icons[];

    public Calculate simulation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        AppRater.app_launched(this, MainActivity.this);
        cardsNames = new String[]{getString(R.string.noActiveTarot), "Energy Card ","Cloud Card","Lucky Card","Trick Card","Copycat Card","Blight Card","Mushroom Card","Rebirth Card", "Power Card"};
        icons = new int[]{R.drawable.tarot_house, R.drawable.energy_card , R.drawable.cloud_card, R.drawable.lucky_card, R.drawable.trick_card,
                R.drawable.copycat_card, R.drawable.blight_card , R.drawable.mushroom_card, R.drawable.rebirth_card, R.drawable.power_card};
        starNumber = findViewById(R.id.stars_number_edit);
        diceNumber = findViewById(R.id.dice_number);
        luckyDiceNumber = findViewById(R.id.lucky_dice_number);
        position = findViewById(R.id.position);
        mushroom1Level = findViewById(R.id.mushroom_1_Level_edit);
        mushroom2Level = findViewById(R.id.mushroom_2_Level_edit);
        mushroom3Level = findViewById(R.id.mushroom_3_Level_edit);

        smartThrow = findViewById(R.id.useSmart);
        useNo19 = findViewById(R.id.useNo19);
        useSmartCard = findViewById(R.id.useLuckyCard);
        useKarmaOut = findViewById(R.id.useKarmaOut);

        loadingBar = findViewById(R.id.progressBar);
        progresLabel = findViewById(R.id.progress_label);
        min = findViewById(R.id.min_result_label);
        max = findViewById(R.id.max_result_label);
        average = findViewById(R.id.average_result_label);
        prob80 = findViewById(R.id.prob80_result_label);
        prob110 = findViewById(R.id.prob110_result_label);
        prob140 = findViewById(R.id.prob140_result_label);
        prob170 = findViewById(R.id.prob170_result_label);
        prob200 = findViewById(R.id.prob200_result_label);
        prob230 = findViewById(R.id.prob230_result_label);
        prob260 = findViewById(R.id.prob260_result_label);
        prob300 = findViewById(R.id.prob300_result_label);
        spiritLabel = findViewById(R.id.spirit_result_label);
        magicDustLabel = findViewById(R.id.magic_dust_result_label);
        mosterSoulLabel = findViewById(R.id.monster_soul_result_label);
        promotionStoneLabel = findViewById(R.id.promotion_stone_result_label);
        chaosStoneLabel = findViewById(R.id.chaos_stone_result_label);
        shards3Label = findViewById(R.id.shards_3_result_label);
        shards4Label = findViewById(R.id.shards_4_result_label);
        shards5Label = findViewById(R.id.shards_5_result_label);
        simulateBtn = findViewById(R.id.simulateBtn);
        stopSimulateBtn = findViewById(R.id.stopSimulateBtn);
        configBtn = findViewById(R.id.settings_button);
        helpBtn = findViewById(R.id.help_button);
        donateBtn = findViewById(R.id.donate_button);
        homeBtn = findViewById(R.id.homeButton);
        informationBlock = findViewById(R.id.information_block);
        formBlock = findViewById(R.id.form_block);
        helpBlock = findViewById(R.id.help_block);
        loadingBlock = findViewById(R.id.loading_block);
        resultBlock = findViewById(R.id.results_block);
        configSaveBtn = findViewById(R.id.save_config_btn);
        configSimBar = findViewById(R.id.conf_sim_bar);
        configSimBarLabel = findViewById(R.id.config_sim_label);
        configSimBarAlerLabel = findViewById(R.id.config_sim_alert_label);
        configBlock = findViewById(R.id.settings_block);
        cardsBlock = findViewById(R.id.cards_block);
        donateBlock = findViewById(R.id.donate_block);
        resourcesBlock = findViewById(R.id.resources_block);
        shareBtn = findViewById(R.id.share_button);
        versionText = findViewById(R.id.version);

        luckyCardValue  = findViewById(R.id.lucky_card_result);
        powerCardValue  = findViewById(R.id.power_card_result);
        energyCardValue = findViewById(R.id.energy_card_result);
        mushroomCardValue = findViewById(R.id.mashroom_card_result);
        cloudCardValue = findViewById(R.id.cloud_card_result);
        rebirthCardValue = findViewById(R.id.rebirth_card_result);
        trickCardValue = findViewById(R.id.trick_card_result);
        bilghtCardValue = findViewById(R.id.blight_card_result);
        copycatCardValue = findViewById(R.id.copycat_card_result);


        spinner = findViewById(R.id.spinner);

        loadConfig();

        CustomSpinnerAdapter customAdapter=new CustomSpinnerAdapter(getApplicationContext(),this.icons,this.cardsNames);
        spinner.setAdapter(customAdapter);

        diceNumber.getRootView().clearFocus();



        TooltipCompat.setTooltipText(smartThrow, getString(R.string.optimize_lucky_dices_hint));
        TooltipCompat.setTooltipText(useNo19, getString(R.string.strategy_not_in_19_hint));
        TooltipCompat.setTooltipText(useSmartCard, getString(R.string.strategy_smart_lucky_card_hint));
        TooltipCompat.setTooltipText(useKarmaOut, getString(R.string.strategy_karma_out_hint));

        configSimBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    switch (progress){
                        case 0: configSimBarLabel.setText("100 "+getString(R.string.simulations));
                                configSimBar.getProgressDrawable().setTint(getResources().getColor(R.color.colorAccent));
                                configSimBar.getThumb().setTint(getResources().getColor(R.color.colorAccent));
                                configSimBarAlerLabel.setVisibility(View.GONE);
                                break;
                        case 1: configSimBarLabel.setText("1000 "+getString(R.string.simulations));
                                configSimBar.getProgressDrawable().setTint(getResources().getColor(R.color.colorAccent));
                                configSimBar.getThumb().setTint(getResources().getColor(R.color.colorAccent));
                                configSimBarAlerLabel.setVisibility(View.GONE);
                                break;
                        case 2: configSimBarLabel.setText("10 000 "+getString(R.string.simulations));
                                configSimBar.getProgressDrawable().setTint(getResources().getColor(R.color.colorAccent));
                                configSimBar.getThumb().setTint(getResources().getColor(R.color.colorAccent));
                                configSimBarAlerLabel.setVisibility(View.GONE);
                                break;
                        case 3: configSimBarLabel.setText("100 000 "+getString(R.string.simulations));
                                configSimBar.getProgressDrawable().setTint(getResources().getColor(R.color.colorAccent));
                                configSimBar.getThumb().setTint(getResources().getColor(R.color.colorAccent));
                                configSimBarAlerLabel.setVisibility(View.GONE);
                                break;
                        case 4: configSimBarLabel.setText("1 000 000 "+getString(R.string.simulations));
                                configSimBarAlerLabel.setVisibility(View.VISIBLE);
                                configSimBar.getProgressDrawable().setTint(getResources().getColor(R.color.colorWarning));
                                configSimBar.getThumb().setTint(getResources().getColor(R.color.colorWarning));
                                break;
                    }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        hotValidations();

    }

    public void loadConfig(){
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        config = new Config();
        config.setSimulationsNumber(preferences.getInt("sim_number", 100000));

        switch (config.getSimulationsNumber()){
            case 100: configSimBar.setProgress(0); configSimBarLabel.setText("100 "+getString(R.string.simulations)); break;
            case 1000: configSimBar.setProgress(1); configSimBarLabel.setText("1000 "+getString(R.string.simulations)); break;
            case 10000: configSimBar.setProgress(2); configSimBarLabel.setText("10 000 "+getString(R.string.simulations)); break;
            case 100000: configSimBar.setProgress(3); configSimBarLabel.setText("100 000 "+getString(R.string.simulations)); break;
            case 1000000: configSimBar.setProgress(4); configSimBarLabel.setText("1 000 000 "+getString(R.string.simulations)); break;
        }

        config.setSmartThrow(preferences.getBoolean("smarThrow", true));
        config.setNotIn19(preferences.getBoolean("isNotIn19", true));
        config.setSmartLuckyCard(preferences.getBoolean("isSmartLuckyCard", true));
        config.setKarmaOut(preferences.getBoolean("isKarmaOut", false));

        smartThrow.setChecked(config.getSmartThrow());
        useNo19.setChecked(config.getNotIn19());
        useSmartCard.setChecked(config.getSmartLuckyCard());
        useKarmaOut.setChecked(config.getKarmaOut());
    }

    public void saveConfig(View view){
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        switch (configSimBar.getProgress()){
            case 0: editor.putInt("sim_number", 100); config.setSimulationsNumber(100); break;
            case 1: editor.putInt("sim_number", 1000);  config.setSimulationsNumber(1000); break;
            case 2: editor.putInt("sim_number", 10000);  config.setSimulationsNumber(10000); break;
            case 3: editor.putInt("sim_number", 100000);  config.setSimulationsNumber(100000); break;
            case 4: editor.putInt("sim_number", 1000000);  config.setSimulationsNumber(1000000); break;
        }

        config.setSmartThrow(smartThrow.isChecked());
        config.setNotIn19(useNo19.isChecked());
        config.setSmartLuckyCard(useSmartCard.isChecked());
        config.setKarmaOut(useKarmaOut.isChecked());

        editor.putBoolean("smarThrow", smartThrow.isChecked());
        editor.putBoolean("isNotIn19", useNo19.isChecked());
        editor.putBoolean("isSmartLuckyCard", useSmartCard.isChecked());
        editor.putBoolean("isKarmaOut", useKarmaOut.isChecked());

        editor.commit();
        Toast.makeText(this, getString(R.string.configs_saved), Toast.LENGTH_SHORT).show();
    }

    public void sendMailHelp(View view){
        sendMail(getString(R.string.help_subject));
    }

    public void sendMailDonate(View view){
        sendMail(getString(R.string.donate_subject));
    }

    public void sendMailDonatePaypal(View view){
        sendMail(getString(R.string.donate_paypal_subject));
    }

    public void donatePaypal(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.paypal.me/omv88"));
        startActivity(intent);
    }

    public void shareApk(View view){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = getString(R.string.shared_title)+"\n\nhttps://play.google.com/store/apps/details?id=com.hestingames.impsadventuresim";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.shared_title));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.shared_via)));
    }

    public void sendMail(String subject){
        Uri uri = Uri.parse("mailto:");

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(uri);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hestingames@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

        startActivity(Intent.createChooser(emailIntent, getString(R.string.email_chooser)));
    }

    public void hotValidations(){
        position.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!position.getText().toString().matches(""))
                    if(Integer.parseInt(position.getText().toString()) > 20){
                        position.setText("20");
                        makeToast(R.string.position_validation);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mushroom1Level.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!mushroom1Level.getText().toString().matches(""))
                    if(Integer.parseInt(mushroom1Level.getText().toString()) > 3){
                        mushroom1Level.setText("3");
                        makeToast(R.string.mushroom_field_validation);
                    }
                    if(Integer.parseInt(mushroom1Level.getText().toString()) < 1){
                        mushroom1Level.setText("1");
                        makeToast(R.string.mushroom_field_validation);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mushroom2Level.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!mushroom2Level.getText().toString().matches(""))
                    if(Integer.parseInt(mushroom2Level.getText().toString()) > 3){
                        mushroom2Level.setText("3");
                        makeToast(R.string.mushroom_field_validation);
                    }
                    if(Integer.parseInt(mushroom2Level.getText().toString()) < 1){
                        mushroom2Level.setText("1");
                        makeToast(R.string.mushroom_field_validation);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mushroom3Level.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!mushroom3Level.getText().toString().matches(""))
                    if(Integer.parseInt(mushroom3Level.getText().toString()) > 3){
                        mushroom3Level.setText("3");
                        makeToast(R.string.mushroom_field_validation);
                    }
                    if(Integer.parseInt(mushroom3Level.getText().toString()) < 1){
                        mushroom3Level.setText("1");
                        makeToast(R.string.mushroom_field_validation);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void makeToast(Integer text){
        Toast.makeText(this, getString(text), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void click(View view){
        if(this.starNumber.getText().toString().matches("") || this.diceNumber.getText().toString().matches("") || this.position.getText().toString().matches("") || this.mushroom1Level.getText().toString().matches("") || this.mushroom2Level.getText().toString().matches("") || this.mushroom3Level.getText().toString().matches("")  ){
            Toast.makeText(this, getText(R.string.required_fields), Toast.LENGTH_SHORT).show();
        } else if(Integer.parseInt(this.mushroom1Level.getText().toString()) > 3 || Integer.parseInt(this.mushroom2Level.getText().toString()) > 3 || Integer.parseInt(this.mushroom3Level.getText().toString()) > 3 || Integer.parseInt(this.mushroom1Level.getText().toString()) < 1 || Integer.parseInt(this.mushroom2Level.getText().toString()) < 1 || Integer.parseInt(this.mushroom3Level.getText().toString()) < 1 ){
            Toast.makeText(this, getText(R.string.mushroom_field_validation), Toast.LENGTH_SHORT).show();
        } else if(Integer.parseInt(this.position.getText().toString()) > 20){
            Toast.makeText(this, getText(R.string.position_validation), Toast.LENGTH_SHORT).show();
        } else {
            this.simulation = new Calculate();
            this.simulation.execute();
        }
    }

    public void cancelSimulation(View view){
        new AlertDialog.Builder(this)
        .setIcon(R.drawable.icon)
        .setTitle(getString(R.string.simulation_stop_title))
        .setMessage(getString(R.string.simulation_stop_text))
        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelSimulationProccess();
            }

        })
        .setNegativeButton(getString(R.string.no), null)
        .show();
    }

    public void cancelSimulationProccess(){
        this.simulation.cancel(true);
        stopSimulateBtn.setVisibility(View.GONE);
        simulateBtn.setVisibility(View.VISIBLE);
        loadingBlock.setVisibility(View.GONE);
        informationBlock.setVisibility(View.VISIBLE);
        diceNumber.setEnabled(true);
        luckyDiceNumber.setEnabled(true);
        position.setEnabled(true);
        starNumber.setEnabled(true);
        mushroom1Level.setEnabled(true);
        mushroom2Level.setEnabled(true);
        mushroom3Level.setEnabled(true);
        helpBtn.setEnabled(true);
        simulateBtn.setEnabled(true);
        donateBtn.setEnabled(true);
        configBtn.setEnabled(true);
        smartThrow.setEnabled(true);
        spinner.setEnabled(true);
        shareBtn.setEnabled(true);
    }


    public void showHelp(View view){
        showComponent(helpBlock, helpBtn);
    }
    public void config(View view){
        loadConfig();
        showComponent(configBlock, configBtn);
    }
    public void home(View view){
        showComponent(helpBtn, helpBtn);
    }


    public void donate(View view){
        showComponent(donateBlock, donateBtn);
    }

    private void showComponent(View component, View button){
        if(component.getVisibility() == View.GONE){
            donateBlock.setVisibility(View.GONE);
            resultBlock.setVisibility(View.GONE);
            formBlock.setVisibility(View.GONE);
            informationBlock.setVisibility(View.GONE);
            helpBlock.setVisibility(View.GONE);
            configBlock.setVisibility(View.GONE);
            resourcesBlock.setVisibility(View.GONE);
            cardsBlock.setVisibility(View.GONE);

            donateBtn.setSelected(false);
            configBtn.setSelected(false);
            helpBtn.setSelected(false);

            component.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            versionText.setVisibility(View.GONE);

            button.setSelected(true);
        }else{
            donateBlock.setVisibility(View.GONE);
            resultBlock.setVisibility(View.GONE);
            formBlock.setVisibility(View.GONE);
            informationBlock.setVisibility(View.GONE);
            helpBlock.setVisibility(View.GONE);
            configBlock.setVisibility(View.GONE);
            resourcesBlock.setVisibility(View.GONE);
            cardsBlock.setVisibility(View.GONE);

            donateBtn.setSelected(false);
            configBtn.setSelected(false);
            helpBtn.setSelected(false);

            formBlock.setVisibility(View.VISIBLE);
            informationBlock.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.GONE);
            versionText.setVisibility(View.VISIBLE);
        }
    }

    public void showResults(Stats result){
        average.setText(result.getAverage());
        min.setText(getString(R.string.min)+": "+result.getMin());
        max.setText(getString(R.string.max)+": "+result.getMax());
        prob80.setText(result.getProb80());
        prob110.setText(result.getProb110());
        prob140.setText(result.getProb140());
        prob170.setText(result.getProb170());
        prob200.setText(result.getProb200());
        prob230.setText(result.getProb230());
        prob260.setText(result.getProb260());
        prob300.setText(result.getProb300());

        spiritLabel.setText(result.getSpiritAverage());
        magicDustLabel.setText(result.getMagicDustAverage());
        mosterSoulLabel.setText(result.getMosterSoulAverage());
        promotionStoneLabel.setText(result.getPromotionStoneAverage());
        chaosStoneLabel.setText(result.getChaosSotneAverage());
        shards3Label.setText(result.getShards3Average());
        shards4Label.setText(result.getShards4Average());
        shards5Label.setText(result.getShards5Average());

        energyCardValue.setText(result.obtainedCards.get(ETarotEffectType.EnergyCard).toString());
        bilghtCardValue.setText(result.obtainedCards.get(ETarotEffectType.BlightCard).toString());
        cloudCardValue.setText(result.obtainedCards.get(ETarotEffectType.CloudCard).toString());
        mushroomCardValue.setText(result.obtainedCards.get(ETarotEffectType.MushroomCard).toString());
        luckyCardValue.setText(result.obtainedCards.get(ETarotEffectType.LuckyCard).toString());
        powerCardValue.setText(result.obtainedCards.get(ETarotEffectType.PowerCard).toString());
        rebirthCardValue.setText(result.obtainedCards.get(ETarotEffectType.RebirthCard).toString());
        trickCardValue.setText(result.obtainedCards.get(ETarotEffectType.TrickCard).toString());
        copycatCardValue.setText(result.obtainedCards.get(ETarotEffectType.CopycatCard).toString());


        simulateBtn.setVisibility(View.VISIBLE);
        stopSimulateBtn.setVisibility(View.GONE);
        loadingBlock.setVisibility(View.GONE);
        resultBlock.setVisibility(View.VISIBLE);
        resourcesBlock.setVisibility(View.VISIBLE);
        cardsBlock.setVisibility(View.VISIBLE);
        diceNumber.setEnabled(true);
        luckyDiceNumber.setEnabled(true);
        position.setEnabled(true);
        starNumber.setEnabled(true);
        mushroom1Level.setEnabled(true);
        mushroom2Level.setEnabled(true);
        mushroom3Level.setEnabled(true);
        helpBtn.setEnabled(true);
        simulateBtn.setEnabled(true);
        donateBtn.setEnabled(true);
        configBtn.setEnabled(true);
        smartThrow.setEnabled(true);
        spinner.setEnabled(true);
        shareBtn.setEnabled(true);
    }


    @Override
    public void onBackPressed() {
        if(formBlock.getVisibility() == View.GONE){
            donateBlock.setVisibility(View.GONE);
            configBlock.setVisibility(View.GONE);
            resultBlock.setVisibility(View.GONE);
            cardsBlock.setVisibility(View.GONE);
            formBlock.setVisibility(View.VISIBLE);
            informationBlock.setVisibility(View.VISIBLE);
            helpBlock.setVisibility(View.GONE);
            homeBtn.setVisibility(View.GONE);
            resourcesBlock.setVisibility(View.GONE);
        }else{
            new AlertDialog.Builder(this)
            .setIcon(R.drawable.icon)
            .setTitle(getString(R.string.close_confirm_tile))
            .setMessage(getString(R.string.close_confirm_text))
            .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                    System.exit(0);
                }

            })
            .setNegativeButton(getString(R.string.no), null)
            .show();
        }
    }

    private LinkedList<StrategyEnum> loadStrategies(){
        LinkedList<StrategyEnum> activeStrategies = new LinkedList<>();
        activeStrategies.add(StrategyEnum.NormalDice);
        activeStrategies.add(StrategyEnum.BaseSmart);
        activeStrategies.add(StrategyEnum.NormalDice);
        activeStrategies.add(StrategyEnum.LookForNormalDices);

        //Usar dados especiales para obtener mas dados especiales.
        if(config.getSmartThrow())
        activeStrategies.add(StrategyEnum.LookForDices);

        //No usar dados especiales para obtener otro cuando estas en la casilla 18.
        if(config.getNotIn19())
        activeStrategies.add(StrategyEnum.NotSpecialIn18);

        //Usar dado especial cuando esta la carta de la suerte para avanzar 10 pasos y obtener otro dado.
        if(config.getSmartLuckyCard())
        activeStrategies.add(StrategyEnum.UseLuckyWithSpecial);

        //Usar dado especial extra para salir del Karma.
        if(config.getKarmaOut())
        activeStrategies.add(StrategyEnum.OutFromKarma);

        return activeStrategies;
    }

    public class Calculate extends AsyncTask<Stats, Integer, Stats> {

        int starsNumber;
        int dicesNumber;
        int luckyDicesNumber;
        int positionStrart;
        int mush1;
        int mush2;
        int mush3;
        boolean smart;
        int symNumber;
        ETarotEffectType activeTarot;
        LinkedList<StrategyEnum> activeStrategies;

        @Override
        protected void onPreExecute() {
            if(!isCancelled()) {
                super.onPreExecute();
                starsNumber = Integer.parseInt(starNumber.getText().toString());
                dicesNumber = Integer.parseInt(diceNumber.getText().toString());
                luckyDicesNumber = Integer.parseInt(luckyDiceNumber.getText().toString());
                positionStrart = Integer.parseInt(position.getText().toString()) - 1;
                mush1 = Integer.parseInt(mushroom1Level.getText().toString());
                mush2 = Integer.parseInt(mushroom2Level.getText().toString());
                mush3 = Integer.parseInt(mushroom3Level.getText().toString());
                smart = smartThrow.isChecked();
                activeTarot = ETarotEffectType.getById(spinner.getSelectedItemPosition());
                symNumber = config.getSimulationsNumber();
                spinner.setEnabled(false);
                diceNumber.setEnabled(false);
                smartThrow.setEnabled(false);
                luckyDiceNumber.setEnabled(false);
                position.setEnabled(false);
                starNumber.setEnabled(false);
                mushroom1Level.setEnabled(false);
                mushroom2Level.setEnabled(false);
                mushroom3Level.setEnabled(false);
                helpBtn.setEnabled(false);
                simulateBtn.setEnabled(false);
                donateBtn.setEnabled(false);
                configBtn.setEnabled(false);
                shareBtn.setEnabled(false);
                informationBlock.setVisibility(View.GONE);
                resultBlock.setVisibility(View.GONE);
                loadingBlock.setVisibility(View.VISIBLE);
                resourcesBlock.setVisibility(View.GONE);
                cardsBlock.setVisibility(View.GONE);
                loadingBar.setMax(100);
                loadingBar.setProgress(0);
                progresLabel.setText("0%");
                simulateBtn.setVisibility(View.GONE);
                stopSimulateBtn.setVisibility(View.VISIBLE);
                activeStrategies = loadStrategies();
            }
        }


        @Override
        protected Stats doInBackground(Stats... strings) {
            if(!isCancelled()) {
                Stats simulation = simulate(symNumber, starsNumber, dicesNumber, luckyDicesNumber, smart, positionStrart, mush1, mush2, mush3, activeTarot);
                return simulation;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            loadingBar.setProgress(values[0]);
            progresLabel.setText(values[0]+"% "+getString(R.string.of)+" "+symNumber+" "+getString(R.string.simulations));
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, getString(R.string.simulation_stoped), Toast.LENGTH_SHORT).show();
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Stats result){
            showResults(result);
        }

        private Double getProbabilities(int sucess, int intent) {
            return ((double)sucess/(double)intent)*100f;
        }

        public Stats simulate(int numberOfSim, int initalStars, int initialDices, int initialLuckyDices, boolean initialSmart, int initialPosition, int InitialMLevel1, int InitialMLevel2, int InitialMLevel3, ETarotEffectType activeTarot) {
            Board b = new Board(initalStars, initialDices, initialLuckyDices, initialSmart, initialPosition, InitialMLevel1, InitialMLevel2, InitialMLevel3, activeTarot, activeStrategies);
            int simulations = numberOfSim;
            BigInteger bigSim = new BigInteger(String.valueOf(numberOfSim));

            LinkedList<SimulationStats> allSimulations = new LinkedList<>();
            int min80  = 0;
            int min110 = 0;
            int min140 = 0;
            int min170 = 0;
            int min200 = 0;
            int min230 = 0;
            int min260 = 0;
            int min300 = 0;
            int min = 999999999;
            int max = 0;
            int total = 0;
            HashMap obtainedCards = new HashMap();
            BigInteger totalSpirit = new BigInteger("0");
            BigInteger totalMagicDust = new BigInteger("0");
            BigInteger totalMosterSoul = new BigInteger("0");
            BigInteger totalPromotioStone = new BigInteger("0");
            BigInteger totalChaosStone = new BigInteger("0");
            BigInteger totalShards3 = new BigInteger("0");
            BigInteger totalShards4 = new BigInteger("0");
            BigInteger totalShards5 = new BigInteger("0");
            int porcent = 0;

            for (int i = 0; i < simulations; i++) {
                if(isCancelled())
                    break;
                b.StartSimulation();
                SimulationStats s = b.GetFinalStatistics();
                allSimulations.add(s);

                total += s.StarsEarned;
                totalSpirit = totalSpirit.add(new BigInteger(String.valueOf(s.SpiritEarned))) ;
                totalMagicDust = totalMagicDust.add(new BigInteger(String.valueOf(s.MagicDustEarned))) ;
                totalMosterSoul = totalMosterSoul.add(new BigInteger(String.valueOf(s.MosterSoul))) ;
                totalPromotioStone = totalPromotioStone.add(new BigInteger(String.valueOf(s.promotionStoneEarned))) ;
                totalChaosStone = totalChaosStone.add(new BigInteger(String.valueOf(s.chaosStoneEarned))) ;
                totalShards3 = totalShards3.add(new BigInteger(String.valueOf(s.Shards3starsEarned))) ;
                totalShards4 = totalShards4.add(new BigInteger(String.valueOf(s.Shards4starsEarned))) ;
                totalShards5 = totalShards5.add(new BigInteger(String.valueOf(s.Shards5starsEarned))) ;


                if(s.StarsEarned > max)
                    max = s.StarsEarned;

                if(s.StarsEarned < min)
                    min = s.StarsEarned;

                if(s.StarsEarned >= 300)
                    min300++;

                if(s.StarsEarned >= 260)
                    min260++;

                if(s.StarsEarned >= 230)
                    min230++;

                if(s.StarsEarned >= 200)
                    min200++;

                if(s.StarsEarned >= 170)
                    min170++;

                if(s.StarsEarned >= 140)
                    min140++;

                if(s.StarsEarned >= 110)
                    min110++;

                if(s.StarsEarned >= 80)
                    min80++;

                int temp = (i*100)/simulations;

                if(temp > porcent) {
                    porcent = temp;
                    publishProgress(porcent);
                }

                obtainedCards = s.recibedCards;

                b.RestartBoard();

            }

            return new Stats(max, min, (total/simulations), getProbabilities(min80,simulations) , getProbabilities(min110,simulations), getProbabilities(min140,simulations), getProbabilities(min170,simulations), getProbabilities(min200,simulations), getProbabilities(min230,simulations), getProbabilities(min260,simulations), getProbabilities(min300,simulations),
                    (totalSpirit.divide(bigSim).intValue()), (totalMagicDust.divide(bigSim).intValue()), (totalMosterSoul.divide(bigSim).intValue()), (totalPromotioStone.divide(bigSim).intValue()), (totalChaosStone.divide(bigSim).intValue()), (totalShards3.divide(bigSim).intValue()), (totalShards4.divide(bigSim).intValue()), (totalShards5.divide(bigSim).intValue()), obtainedCards);

        }
    }
}
