require 'marky_markov'

markov = MarkyMarkov::TemporaryDictionary.new
markov.parse_file "output/bodies.txt"
1000.times do
  puts markov.generate_n_sentences 1
end
