package com.bdswiss.bdswiss.data.rates.datasources.remote;

import static com.bdswiss.bdswiss.data.ApiDef.sPRICE;
import static com.bdswiss.bdswiss.data.ApiDef.sSYMBOL;
import static com.bdswiss.bdswiss.data.JsonUtils.createEntity;
import static com.bdswiss.bdswiss.data.JsonUtils.getRatesBlock;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bdswiss.bdswiss.data.JsonUtils;
import com.bdswiss.bdswiss.data.rates.model.RateAM;
import com.bdswiss.bdswiss.data.rates.model.RateAMWrapper;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.ToJson;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RatesJsonAdapter extends JsonAdapter<RateAMWrapper> {

  @FromJson
  @Nullable
  @Override
  public RateAMWrapper fromJson(@NonNull final JsonReader jsonReader) throws IOException {
    try {
      final JSONObject jsonObject = JsonUtils.getJSONObject(jsonReader);
      final JSONArray dataJsonArray = getRatesBlock(jsonObject);
      if (dataJsonArray != null) {
        final int size = dataJsonArray.length();
        final ArrayList<RateAM> ratesAM = new ArrayList<>(size);
        JSONObject rateObject;
        RateAM rateAM;
        for (int i = 0; i < size; i++) {
          rateObject = dataJsonArray.getJSONObject(i);
          rateAM = createEntity(rateObject, RateAM.class);
          if (rateAM != null) {
            rateAM.setMPrice(rateObject.getDouble(sPRICE));
            rateAM.setMSymbol(rateObject.getString(sSYMBOL));
            ratesAM.add(rateAM);
          }
        }
        return new RateAMWrapper(ratesAM);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  @ToJson
  @Override
  public void toJson(@NonNull final JsonWriter writer, @Nullable final RateAMWrapper value) {
    throw new UnsupportedOperationException();
  }
}
