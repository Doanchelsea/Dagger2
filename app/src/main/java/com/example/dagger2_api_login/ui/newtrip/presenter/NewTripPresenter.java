package com.example.dagger2_api_login.ui.newtrip.presenter;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.model.historyDetail.Result;
import com.example.dagger2_api_login.ui.newtrip.contract.NewTripContract;
import com.example.dagger2_api_login.untils.ErrorHandler;
import com.example.dagger2_api_login.untils.StringUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


public class NewTripPresenter extends RxPresenter<NewTripContract.View>
        implements NewTripContract.Presenter<NewTripContract.View> {

    private List<AutocompletePrediction> predictionListok;
    private DataManager dataManager;

    @Inject
    public NewTripPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void SeachBar(MaterialSearchBar materialSearchBar,
                         List<AutocompletePrediction> predictionList,
                         PlacesClient mplacesClient,InputMethodManager inm) {
        predictionListok = predictionList;
        final AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

            }

            @Override
            public void onButtonClicked(int buttonCode) {
                if (buttonCode == MaterialSearchBar.BUTTON_NAVIGATION){
                    // mo NAVIGATION

                }else if (buttonCode == MaterialSearchBar.BUTTON_BACK){
                    materialSearchBar.disableSearch();
                }
            }
        });

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FindAutocompletePredictionsRequest predictionsRequest = FindAutocompletePredictionsRequest.builder()
                        .setCountry("vn")
                        .setTypeFilter(TypeFilter.ADDRESS)
                        .setSessionToken(token)
                        .setQuery(charSequence.toString())
                        .build();
                mplacesClient.findAutocompletePredictions(predictionsRequest).addOnCompleteListener(new OnCompleteListener<FindAutocompletePredictionsResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<FindAutocompletePredictionsResponse> task) {
                        if (task.isSuccessful()){
                           final FindAutocompletePredictionsResponse predictionsResponse = task.getResult();
                            if (predictionsResponse != null){
                                predictionListok = predictionsResponse.getAutocompletePredictions();
                                List<String> stringList = new ArrayList<>();
                                for (int i=0; i<predictionListok.size();i++){
                                    AutocompletePrediction prediction = predictionListok.get(i);
                                    stringList.add(prediction.getFullText(null).toString());
                                }
                                materialSearchBar.updateLastSuggestions(stringList);
                                if (!materialSearchBar.isSuggestionsVisible()){
                                    // không tìm ký tự tìm kiếm
                                    materialSearchBar.clearSuggestions();
                                }
                            }

                        }else {

                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        materialSearchBar.setSuggestionsClickListener(new SuggestionsAdapter.OnItemViewClickListener() {
            @Override
            public void OnItemClickListener(int position, View v) {

                if (position >= predictionListok.size()){
                    return;
                }

                AutocompletePrediction selectedPrediction = predictionListok.get(position);
                final String suggestion = materialSearchBar.getLastSuggestions().get(position).toString();
                materialSearchBar.setText(suggestion);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialSearchBar.clearSuggestions();
                    }
                },1000);
                if (inm != null){
                    inm.hideSoftInputFromWindow(materialSearchBar.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
                    String placeId = selectedPrediction.getPlaceId();
                    List<Place.Field> placeField = Arrays.asList(Place.Field.LAT_LNG,Place.Field.NAME);

                    FetchPlaceRequest fetchPlaceRequest = FetchPlaceRequest.builder(placeId,placeField).build();
                    mplacesClient.fetchPlace(fetchPlaceRequest).addOnSuccessListener(runnable -> {
                        final Place place = runnable.getPlace();
                        mView.ShowLatLng(place.getLatLng().latitude,place.getLatLng().longitude,place.getName());
                    }).addOnFailureListener(failure ->{
                        mView.error(R.string.common_noti_error_message);
                    });
                }
            }
            @Override
            public void OnItemDeleteListener(int position, View v) {

            }
        });
    }

    @Override
    public void showResult(double lat, double lng,double locationLat, double locationLng) {

        Token token = dataManager.getToken();

        if (token == null) {
            mView.showError(R.string.common_noti_error);
            return;
        }

        String tokenKey = dataManager.getToken().getTokenKey();
        if (StringUtils.isEmpty(tokenKey)) {
            mView.showError(R.string.common_noti_error);
            return;
        }

        Map<String, String> httpBody = new HashMap<>();
        Log.d("aaaaaaaaaaaaaa",locationLat+":"+locationLng);
        httpBody.put("latitude",String.valueOf(locationLat));
        httpBody.put("longitude",String.valueOf(locationLng));

        Map<String, String> httpBody1 = new HashMap<>();
        httpBody1.put("latitude",String.valueOf(lat));
        httpBody1.put("longitude",String.valueOf(lng));

        Map<String, String> httpBody2 = new HashMap<>();
        httpBody2.put("latitude","0.0");
        httpBody2.put("longitude","0.0");

        Map<String,Object> hasmap = new HashMap<>();
        hasmap.put("startlocation",httpBody);
        hasmap.put("dropOffOne",httpBody1);
        hasmap.put("dropOffTwo",httpBody2);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(hasmap)).toString());

        Disposable disposable = dataManager.getLstCarType(tokenKey,body)
                .flatMap(historyDetail -> Observable.just(historyDetail.getResults().getResult()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    if (results == null){
                        return;
                    }
                    mView.show(results);
                },throwable -> {
                    mView.error(R.string.common_noti_error_message);
                });
        addSubscribe(disposable);
    }

    @Override
    public void jsonString(String jsonString) {

        Token token = dataManager.getToken();
        if (token == null) {
            mView.showError(R.string.common_noti_error);
            return;
        }

        String tokenKey = dataManager.getToken().getTokenKey();
        if (StringUtils.isEmpty(tokenKey)) {
            mView.showError(R.string.common_noti_error);
            return;
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (jsonString));

        Disposable disposable = dataManager.findTripByLocation(tokenKey,body)
                .flatMap(error -> Observable.just(error.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(error -> {
                    mView.ShowSucess();
                },throwable -> {
                    mView.ShowErrorFind(ErrorHandler.errorParser(throwable),dataManager);
                    Log.d("dâdada",throwable.toString());
                });
        addSubscribe(disposable);
    }

    @Override
    public void getLastEventBus() {
        Token token = dataManager.getToken();
        if (token == null) {
            return;
        }
        String tokenKey = token.getTokenKey();
        if (StringUtils.isEmpty(tokenKey)) {
            mView.showError(R.string.common_noti_error_message);
            return;
        }

        Disposable disposable = dataManager.getLastStatus(tokenKey)
                .flatMap(history -> Observable.just(history.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultsHis -> {
                    mView.showTripPackge(resultsHis);
                }, throwable -> {
                    mView.showError(R.string.common_noti_error);
                });

        addSubscribe(disposable);
    }


}
