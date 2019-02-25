package com.mx.testnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.mx.library.retrofit.Book;
import com.mx.library.retrofit.RetrofitService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class Main4Activity extends AppCompatActivity {

    TextView textView, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView = findViewById(R.id.text_view);
        textView1 = findViewById(R.id.text_view1);

    }

    public void onClickButton(View view) {
        Observable<String> observable = getObservable();
        Observer<String> observer = getObserver();
//        observable.subscribe(observer);
        observable.subscribe(new Consumer<String>() {
            StringBuilder sb = new StringBuilder();

            @Override
            public void accept(String s) throws Exception {
                sb.append(s);
                textView.setText(sb);
            }
        });

    }

    public Observable<String> getObservable() {

//        return Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("test");
//                e.onComplete();
//            }
//        });

        Observable observable = Observable.just("test1", "test2");
        return observable;
    }

    public Observer<String> getObserver() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
//                d.dispose();
            }

            @Override
            public void onNext(String value) {
                textView.setText(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        return observer;
    }

    public void onClickButton1(View view) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Observable<Book> observable = service.getSearchBook("金瓶梅", null, 0, 1);

        observable.subscribe(new Consumer<Book>() {
            @Override
            public void accept(Book book) throws Exception {
                textView1.setText(book.getBooks().get(0).getAlt_title());
            }
        });


    }
}
