package com.bdswiss.bdswiss.data;


import static com.bdswiss.bdswiss.data.ApiDef.sRATES;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

  public JsonUtils() {
    throw new AssertionError();
  }

  @SuppressWarnings("unchecked")
  public static JSONObject getJSONObject(@NonNull final JsonReader jsonReader)
      throws IOException, JSONException {
    return new JSONObject(new Gson().toJson(jsonReader.readJsonValue()));
  }

  @Nullable
  public static <T> T createEntity(@NonNull final JSONObject jsonObject, Class<T> type)
      throws IOException, JSONException {
    final T entity;
    final Moshi moshi = MoshiClient.getDefaultMoshiClient();
    entity = moshi.adapter(type).fromJson(jsonObject.toString());
    return entity;
  }

  @Nullable
  @SuppressWarnings("unchecked")
  public static JSONArray getRatesBlock(@NonNull final JSONObject jsonObject) {
    if (hasAndIsNotNull(jsonObject, sRATES)) {
      final JSONObject optJsonObject = jsonObject.optJSONObject(sRATES);
      if (optJsonObject != null) {
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put(optJsonObject);
        return jsonArray;
      } else {
        final JSONArray optJsonArray = jsonObject.optJSONArray(sRATES);
        if (optJsonArray != null) {
          return optJsonArray;
        }
      }
    }
    return null;
  }

  private static boolean hasAndIsNotNull(
      @NonNull final JSONObject object, @NonNull final String member) {
    return object.has(member) && !object.isNull(member);
  }
}
