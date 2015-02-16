# What is this?

One night I was bored. So, like any intellectually curious, red blooded American, I thought to myself

 > What's Jeb Bush up to?

Well, it turns out, quite a lot. Did you know [he might run for president](http://www.huffingtonpost.com/2015/02/10/jeb-bush-emails_n_6655504.html)? He's also [published all of his emails online](http://jebbushemails.com/home).

So I thought, why not use the resulting text to train a Markov bot and put it online? This is the code that generated the content which is being [posted on Twitter](https://twitter.com/jebsemails).

I asked the bot if it had any plans to run for president, it replied (emphasis added):

 > HURRICANE FLOYD!

 So, as you can see, it's keeping it's options open.

## How do I use it

You'll need Scala and Ruby. You'll also need to download and unpack [the pst files from Jeb's website](http://jebbushemails.com/email/search).

### Building the Scala Library

Scala is used to parse the pst files and output a text file. To build the jar file use the following command:

```sh
sbt assembly
```

This project uses ruby, so you'll need to bundle:

```sh
bundle install
```

### Running

You interact with the library using a command line application (Thor app).

You can find all the from addresses in the pst files using the following command:

```sh
bundle exec ./bin/markov from > output/from.txt
```

If you want to find the most email addresses that appear the most frequently in the from address, use the following command:

```sh
sort output/from.txt | uniq -c | sort -r > output/unique_from.txt
```

Now, it extract the bodies, use the following command:

```sh
bundle exec ./bin/markov bodies --emails /O=BUSH-BROGAN\ 2002/OU=FIRST\ ADMINISTRATIVE\ GROUP/CN=RECIPIENTS/CN=JEB /O=JEB\ BUSH/OU=FIRST\ ADMINISTRATIVE\ GROUP/CN=RECIPIENTS/CN=JEB jeb@jeb.org > output/bodies.txt
```

Next, you can build the model:

```sh
bundle exec ./bin/markov build --content output/bodies.txt
```

And finally, you can generate some sentences:

```sh
bundle exec ./bin/markov generate --count 5
```

