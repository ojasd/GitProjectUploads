package com.ItemRecommender.BookRecommendation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class BookRecommend {

	public static void main(String[] args) {
		UserSimilarity similarity;
		try {
			DataModel dm = new FileDataModel(new File(("data/u.data")));

			ItemSimilarity is = new LogLikelihoodSimilarity(dm);

			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(
					dm, is);

			int x = 1;
			for (LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) {
				long itemID = items.nextLong();
				List<RecommendedItem> recommendations = recommender
						.mostSimilarItems(itemID, 3);

				for (RecommendedItem recommendation : recommendations) {
					System.out.println(itemID + ","
							+ recommendation.getItemID() + ","
							+ recommendation.getValue());
				}
			}
			x++;
			if (x > 10) {
				System.out.println("This is the end");
			}
			
			similarity = new PearsonCorrelationSimilarity(dm);
			UserNeighborhood neighbourhood = new NearestNUserNeighborhood(2,
					similarity, dm);

			Recommender recommend = new GenericUserBasedRecommender(
					dm, neighbourhood, similarity);
			long start = System.currentTimeMillis();
			List<RecommendedItem> recommendations = recommender.recommend(1, 50);
			for (RecommendedItem recommendation : recommendations) {
				System.out.println(recommendation);

			}
			long stop = System.currentTimeMillis();
			System.out.println("Took: " + (stop - start) + " millis");

		for (int i = 1; i <= 50; i++) {
			recommendations = recommender.recommend(
					i, 3);
			for (RecommendedItem recommendation : recommendations) {
				System.out.println("User " + i + " >> " + recommendation);
			}
		}
		stop = System.currentTimeMillis();
		System.out.println("Took: " + (stop - start) + " millis");
		
	}
		
		catch (TasteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There was an exception");
			e.printStackTrace();
		}
	}
}
