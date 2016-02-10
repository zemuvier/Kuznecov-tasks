#!/usr/bin/env ruby
# encoding: utf-8

require "bunny"

conn = Bunny.new(:automatically_recover => false)
conn.start

ch   = conn.create_channel
q    = ch.queue("hello")
puts "Enter the message: "
mes  = gets.chomp()

ch.default_exchange.publish(mes, :routing_key => q.name)
puts " [x] Sent"
puts mes 

conn.close
