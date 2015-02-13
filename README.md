# What is this?

One night I was bored. So, like any intellectually curious, red blooded American, I thought to myself

 > What's Jeb Bush up to?

Well, it turns out, quite a lot. Did you know [he might run for president](http://www.huffingtonpost.com/2015/02/10/jeb-bush-emails_n_6655504.html)? He's also [published all of his emails online](http://jebbushemails.com/home).

So I thought, why not use the resulting text to train a Markov bot and put it online? This is the code that generated the content which is being [posted on Twitter](https://twitter.com/jebsemails).

I asked it the bot if it had any plans to run for president, it replied:

 > Hurricane Floyd.

 So, as you can see, it's keeping it's options open.

## How do I use it

You'll need Scala and Ruby. You'll also need to download and unpack [the pst files from Jeb's website](http://jebbushemails.com/email/search).

### Parse the PST files

Scala is used to parse the pst files and output a text file. To build the jar file use the following command:

```sh
sbt assembly
```

Once you've build the jar, you can run it as follows:

```sh
java -jar target/scala-2.10/main-assembly-0.1.jar <pst file names>
```

For instance, in my case I used the following command

```sh
java -jar target/scala-2.10/main-assembly-0.1.jar files/1999.pst files/2000.pst files/2001.pst files/2002.pst files/2003.pst files/200401-06JanJun.pst files/200407-12JulDec.pst files/200501-06JanJun.pst files/200507-12JulDec.pst files/200601-06JanJun.pst files/200607-12JulDec.pst files/2003\ New/12\ December\ 2003\ Public\ 2.pst files/2003\ New/11\ November\ 2003\ Public\ 2.pst files/2003\ New/10\ October\ 2003\ Public\ 2.pst files/2003\ New/09\ September\ 2003\ Public\ 2.pst files/2003\ New/08\ August\ 2003\ Public\ 2.pst files/2003\ New/07\ July\ 2003\ Public\ 2.pst files/2003\ New/06\ June\ 2003\ Public\ 2.pst files/2003\ New/05\ May\ 2003\ Public\ 2.pst files/2003\ New/04\ April\ 2003\ Public\ 2.pst files/2003\ New/03\ March\ 2003\ Public\ 2.pst files/2003\ New/02\ February\ 2003\ Public\ 2.pst files/2002\ New/12\ December\ 2002\ Public.pst files/2003\ New/01\ January\ 2003\ Public\ 2.pst files/2002\ New/08\ August\ 2002\ Public.pst files/2002\ New/10\ October\ 2002\ Public.pst files/2002\ New/07\ July\ 2002\ Public.pst files/2002\ New/06\ June\ 2002\ Public.pst files/2002\ New/05\ May\ 2002\ Public.pst > email_bodies.txt
```

The code takes all the emails from jeb@jeb.org (he uses other email addresses, but this was good enough) and tries to strip out any text that was populated from replying to other people's emails. The resulting file should be entirely (or at least mostly) text written by Jeb Bush.

### Generate the Markov model

Ruby is used to generate the [Markov model](http://en.wikipedia.org/wiki/Markov_model). Specifically, I'm using the [marky_markov](https://github.com/zolrath/marky_markov) gem.

This is ruby, so you'll need to bundle:

```sh
bundle install
```

Now you should be able to generate 100 random sentences by running the following from `bundle exec irb`

```ruby
require 'marky_markov'

markov = MarkyMarkov::TemporaryDictionary.new
markov.parse_file "email_bodies.txt"
100.times do
  puts markov.generate_n_sentences 1
end
```

## ETC

Find all the pst files in the './files' directory on OS X.

```sh
mdfind -onlyin files -name pst
```

Given a file containing email addresses, find the count of each unique email address. (There is some commented code for outputing email address.)

```sh
sort emails | uniq --count > unique
```


