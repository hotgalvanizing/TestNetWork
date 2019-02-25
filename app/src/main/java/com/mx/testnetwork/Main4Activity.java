package com.mx.testnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mx.library.retrofit.Book;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class Main4Activity extends AppCompatActivity {

    TextView textView,textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView = findViewById(R.id.text_view);
        textView = findViewById(R.id.text_view1);

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

    public Observable<String> getObservable(){

//        return Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("test");
//                e.onComplete();
//            }
//        });

        Observable observable = Observable.just("test1","test2");
        return observable;
    }

    public Observer<String> getObserver(){

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
        Observable.create(new ObservableOnSubscribe<Book>() {
            @Override
            public void subscribe(ObservableEmitter<Book> e) throws Exception {
                Book book = new Book();

                e.onNext(book);
            }
        }).subscribe(new Observer<Book>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Book value) {
                textView1.setText(value.getBooks().get(0).getAlt_title());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
