import tensorflow as tf
from tensorflow import keras
import numpy as np

imdb = keras.datasets.imdb

(train_data, train_labels), (test_data, test_labels) = imdb.load_data(num_words=10000) #keep the top 10,000 most frequent words.

print(train_data[0])

# A dictionary mapping words to an integer index
word_index = imdb.get_word_index() # For example: 'the': 1, 'and': 5

word_index = {k:(v+3) for k,v in word_index.items()} # Add 3 to every word’s index so that we can reserve 0–3 for
word_index["<PAD>"] = 0
word_index["<START>"] = 1
word_index["<UNK>"] = 2  # unknown
word_index["<UNUSED>"] = 3

reverse_word_index = dict([(value, key) for (key, value) in word_index.items()]) #  Flip the word_index dictionary

train_data = keras.preprocessing.sequence.pad_sequences(train_data, value=word_index["<PAD>"], padding="post", maxlen=250)
test_data = keras.preprocessing.sequence.pad_sequences(test_data, value=word_index["<PAD>"], padding="post", maxlen=250)

def decode_review(text): # converts an encoded review (like [1, 14, 22]) into plain English
	return " ".join([reverse_word_index.get(i, "?") for i in text])

# this function will return the decoded (human readable) reviews

print(decode_review(test_data[0]))
#
# model = keras.Sequential()
# model.add(keras.layers.Embedding(88000, 16))
# model.add(keras.layers.GlobalAveragePooling1D())
# model.add(keras.layers.Dense(16, activation="relu"))
# model.add(keras.layers.Dense(1, activation="sigmoid")) #maps any real-valued number into the range (0, 1).
#
# model.summary()  # prints a summary of the model
#
# model.compile(optimizer = "adam", loss = "binary_crossentropy", metrics=["accuracy"]) # Cross-entropy is a loss function used in classification problems to measure how well a predicted probability distribution matches the true labels.
#
# x_val = train_data[:10000]
# x_train = train_data[10000:]
#
# y_val = train_labels[:10000]
# y_train = train_labels[10000:]
#
# fitModel = model.fit(x_train, y_train, epochs=40, batch_size=512, validation_data=(x_val, y_val), verbose=1) #Verbose is like, how detailed of an output is to be printed. Verbose = 2 will give just the accuracy and loss values after each epoch is trained. Verbose=0 will not print anything
# results = model.evaluate(test_data, test_labels)
# print(results)
#
# model.save("model.h5") #We recommend using instead the native Keras format, e.g. `model.save('my_model.keras')` or `keras.saving.save_model(model, 'my_model.keras')`.

def review_encode(s):
	encoded = [1]

	for word in s:
		if word.lower() in word_index:
			encoded.append(word_index[word.lower()])
		else:
			encoded.append(2)

	return encoded

model = keras.models.load_model("model.h5")

with open("test.txt", encoding="utf-8") as f:
	for line in f.readlines():
		nline = line.replace(",", "").replace(".", "").replace("(", "").replace(")", "").replace(":", "").replace("\"","").strip().split(" ")
		encode = review_encode(nline)
		encode = keras.preprocessing.sequence.pad_sequences([encode], value=word_index["<PAD>"], padding="post", maxlen=250) # make the data 250 words long
		predict = model.predict(encode)
		print(line)
		print(encode)
		print(predict[0])


# test_review = test_data[1]
# predict = model.predict(np.array([test_review]))
# print("Review: ")
# print(decode_review(test_review))
# print("Prediction: " + str(predict[0]))
# print("Actual: "+ str(test_labels[1]))
