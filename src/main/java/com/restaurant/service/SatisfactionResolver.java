package com.restaurant.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import com.restaurant.entities.Item;
import com.restaurant.entities.RestaurantMenu;

@Component
public class SatisfactionResolver {

	private RestaurantMenu restaurantMenu;

	public double solve() {
		Collections.sort(restaurantMenu.getItems(), new Comparator<Item>() {
			public int compare(Item i1, Item i2) {
				return Double.compare(i2.getRatio(), i1.getRatio());
			}
		});

		Node best = new Node();
		Node root = new Node();
		root.computeBound();

		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.offer(root);

		while (!q.isEmpty()) {
			Node node = q.poll();

			if (node.bound > best.satisfaction && node.h < restaurantMenu.getItems().size() - 1) {

				Node with = new Node(node);
				Item item = restaurantMenu.getItems().get(node.h);
				with.time += item.getTime();

				if (with.time <= restaurantMenu.getCapacity()) {

					with.taken.add(restaurantMenu.getItems().get(node.h));
					with.satisfaction += item.getSatisfactionValue();
					with.computeBound();

					if (with.satisfaction > best.satisfaction) {
						best = with;
					}
					if (with.bound > best.satisfaction) {
						q.offer(with);
					}
				}

				Node without = new Node(node);
				without.computeBound();

				if (without.bound > best.satisfaction) {
					q.offer(without);
				}
			}
		}
		return best.satisfaction;
	}

	public void setRestaurantMenu(RestaurantMenu restaurantMenu) {
		this.restaurantMenu = restaurantMenu;
	}

	private class Node implements Comparable<Node> {
		public int h;
		List<Item> taken;
		public double bound;
		public double satisfaction;
		public double time;

		public Node() {
			taken = new ArrayList<Item>();
		}

		public Node(Node parent) {
			h = parent.h + 1;
			taken = new ArrayList<Item>(parent.taken);
			bound = parent.bound;
			satisfaction = parent.satisfaction;
			time = parent.time;
		}
		
		public int compareTo(Node other) {
			return (int) (other.bound - bound);
		}

		public void computeBound() {
			int i = h;
			double w = time;
			bound = satisfaction;
			Item item;
			do {
				item = restaurantMenu.getItems().get(i);
				if (w + item.getTime() > restaurantMenu.getCapacity())
					break;
				w += item.getTime();
				bound += item.getSatisfactionValue();
				i++;
			} while (i < restaurantMenu.getItems().size());
			bound += (restaurantMenu.getCapacity() - w) * (item.getSatisfactionValue() / item.getTime());
		}
	}

}
