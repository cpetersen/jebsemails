#!/usr/bin/env ruby

require "marky_markov"
require "thor"

class Markov < Thor
  def from_emails
    find_files.each do |pst_file|
      `java -jar target/scala-2.10/main-assembly-0.1.jar #{pst_file}`
    end
  end

  no_tasks do
    def find_files
      Dir.glob("**/*.pst")
    end
  end
end


markov = MarkyMarkov::TemporaryDictionary.new
markov.parse_file "output/bodies.txt"
1000.times do
  puts markov.generate_n_sentences 1
end
